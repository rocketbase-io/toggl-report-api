package io.rocketbase.toggl.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class RequestContext {

    private final String host;
    private final HttpClient httpClient;
    private final ClientHttpRequestFactory requestFactory;
    private final RestTemplate restTemplate;

    private String basicAuth = null;
    private String apiToken = null;
    private String userAgent = null;
    private Long workspaceId = null;

    private long throttlePeriod = 1000;
    private long lastCall;

    public RequestContext(String host, String apiToken, String userAgent, long workspaceId) {
        this.host = host;
        this.apiToken = apiToken;
        this.userAgent = userAgent;
        this.workspaceId = workspaceId;
        this.httpClient = HttpClientBuilder.create()
                .build();
        this.requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(new MappingJackson2HttpMessageConverter(getObjectMapper())));
    }

    protected ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        return mapper;
    }

    protected String getBasicAuth() {
        if (basicAuth == null) {
            String auth = apiToken + ":api_token";
            basicAuth = new String(Base64.encodeBase64(auth.getBytes()));
        }
        return basicAuth;
    }

    public RestUriBuilder getUriBuilder() {
        return new RestUriBuilder()
                .protocol("https")
                .host(host);
    }

    public synchronized <E> E execute(RestUriBuilder uriBuilder, ParameterizedTypeReference<E> entityClass) throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + getBasicAuth());

        uriBuilder.addParameter("user_agent", userAgent);
        uriBuilder.addParameter("workspace_id", workspaceId);

        checkThrottlePeriod();

        HttpEntity<E> httpEntity = new HttpEntity<E>(headers);
        ResponseEntity<E> response = restTemplate.exchange(uriBuilder.build(), HttpMethod.GET, httpEntity, entityClass);

        lastCall = System.currentTimeMillis();

        return response.getBody();
    }

    protected synchronized void checkThrottlePeriod() {
        while (lastCall + throttlePeriod > System.currentTimeMillis()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore, except to propagate
                Thread.currentThread()
                        .interrupt();
            }
        }
    }

}
