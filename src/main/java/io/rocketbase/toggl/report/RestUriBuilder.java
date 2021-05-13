package io.rocketbase.toggl.report;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class RestUriBuilder {

    private String host;
    private String protocol = "https";
    private StringBuilder path;
    private Map<String, Collection> parameters = new HashMap<>();

    public RestUriBuilder host(String host) {
        Objects.nonNull(host);
        this.host = host;
        return this;
    }

    public RestUriBuilder protocol(String protocol) {
        Objects.nonNull(protocol);
        this.protocol = protocol;
        return this;
    }

    public RestUriBuilder path(String path) {
        Objects.nonNull(path);
        this.path = new StringBuilder();
        this.path.append(path);
        return this;
    }

    public RestUriBuilder appendPath(String path) {
        Objects.nonNull(path);
        this.path.append(path);
        return this;
    }

    public RestUriBuilder addParameter(String key, Object value) {
        return addParameters(key, Collections.singletonList(value));
    }

    public RestUriBuilder addParameters(String key, Collection<? extends Object> value) {
        Objects.nonNull(key);
        Objects.nonNull(value);
        this.parameters.putIfAbsent(key, new ArrayList<>());
        this.parameters.get(key).addAll(value);
        return this;
    }

    public String build() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(protocol + "://" + host + path);
        if (!parameters.isEmpty()) {
            for (Map.Entry<String, Collection> entry : parameters.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.toUriString();
    }
}
