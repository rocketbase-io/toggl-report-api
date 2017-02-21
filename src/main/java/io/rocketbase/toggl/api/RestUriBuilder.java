package io.rocketbase.toggl.api;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.*;

public class RestUriBuilder {

    private String host;
    private String protocol = "https";
    private StringBuilder path;
    private Map<String, List<String>> parameters = new HashMap<String, List<String>>();

    public RestUriBuilder host(String host) {
        Preconditions.checkNotNull(host);
        this.host = host;
        return this;
    }

    public RestUriBuilder protocol(String protocol) {
        Preconditions.checkNotNull(protocol);
        this.protocol = protocol;
        return this;
    }

    public RestUriBuilder path(String path) {
        Preconditions.checkNotNull(path);
        this.path = new StringBuilder();
        this.path.append(path);
        return this;
    }

    public RestUriBuilder appendPath(String path) {
        Preconditions.checkNotNull(path);
        this.path.append(path);
        return this;
    }

    public RestUriBuilder addParameter(String key, Object value) {
        return addParameters(key, Collections.singletonList(value));
    }

    public RestUriBuilder addParameters(String key, Collection<?> value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        Iterable<String> stringValues = Iterables.transform(value, new Function<Object, String>() {
            public String apply(Object o) {
                return String.valueOf(o);
            }
        });
        this.parameters.put(key, Lists.newArrayList(stringValues));
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol);
        builder.append("://");
        Preconditions.checkNotNull(host);
        Preconditions.checkNotNull(path);
        builder.append(host);
        builder.append(path);
        if (!parameters.isEmpty()) {
            builder.append("?");
            Joiner.on("&")
                    .appendTo(builder, Iterables.transform(parameters.entrySet(), new Function<Map.Entry<String, List<String>>, String>() {
                        public String apply(final Map.Entry<String, List<String>> stringStringEntry) {
                            return Joiner.on("&")
                                    .join(Lists.transform(stringStringEntry.getValue(), new Function<String, String>() {
                                        public String apply(String s) {
                                            return stringStringEntry.getKey() + "=" + s;
                                        }
                                    }));
                        }
                    }));
        }
        return builder.toString();
    }
}
