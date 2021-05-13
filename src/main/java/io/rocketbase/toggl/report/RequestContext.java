package io.rocketbase.toggl.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class RequestContext {

    private static long lastCall;
    private final TogglReportApiBuilder apiBuilder;
    private HttpClient httpClient;
    private ClientHttpRequestFactory requestFactory;
    private RestTemplate restTemplate;
    private String basicAuth = null;
    private long throttlePeriod = 1100;

    RequestContext(TogglReportApiBuilder apiBuilder) {
        this.apiBuilder = apiBuilder;

        this.httpClient = HttpClientBuilder.create()
                .build();
        this.requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(requestFactory);
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter(getObjectMapper())));
    }


    protected ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return mapper;
    }

    protected String getBasicAuth() {
        if (basicAuth == null) {
            String auth = apiBuilder.getApiToken() + ":api_token";
            basicAuth = new String(Base64.encodeBase64(auth.getBytes()));
        }
        return basicAuth;
    }

    public RestUriBuilder getUriBuilder() {
        return new RestUriBuilder()
                .protocol("https")
                .host(apiBuilder.getHost());
    }

    public synchronized <E> E execute(RestUriBuilder uriBuilder, ParameterizedTypeReference<E> entityClass) throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + getBasicAuth());

        uriBuilder.addParameter("user_agent", apiBuilder.getUserAgent());

        if (apiBuilder.workspaceProviderPresent()) {
            uriBuilder.addParameter("workspace_id",
                    apiBuilder.getWorkspaceProvider()
                            .getWorkspaceId());
        } else {
            uriBuilder.addParameter("workspace_id", apiBuilder.getWorkspaceId());
        }

        checkThrottlePeriod();

        HttpEntity<E> httpEntity = new HttpEntity<E>(headers);
        ResponseEntity<E> response = restTemplate.exchange(uriBuilder.build(), HttpMethod.GET, httpEntity, entityClass);
        lastCall = System.currentTimeMillis();
        if (apiBuilder.throttleProviderPresent()) {
            apiBuilder.getThrottleProvider()
                    .apiCalled();
        }


        return response.getBody();
    }

    protected synchronized void checkThrottlePeriod() {
        if (apiBuilder.throttleProviderPresent()) {
            waitFuturePassed(apiBuilder.getThrottleProvider()
                    .getNextCallAllowed());
        } else {
            waitFuturePassed(lastCall + throttlePeriod);
        }
    }

    protected synchronized void waitFuturePassed(long future) {
        while (future > System.currentTimeMillis()) {
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
