package io.rocketbase.toggl.api;

import org.springframework.core.ParameterizedTypeReference;

import java.io.IOException;
import java.net.URISyntaxException;

class ExecutableRequestChain<E> extends RequestChain {

    private final ParameterizedTypeReference<E> entityClass;
    private final RestUriBuilder uriBuilder;

    public ExecutableRequestChain(RequestChain parent, String path, ParameterizedTypeReference<E> entityClass) {
        super(parent, path);
        this.uriBuilder = getContext().getUriBuilder();
        this.uriBuilder.path(resolvePath());
        this.entityClass = entityClass;
    }

    public ExecutableRequestChain(RequestContext context, String path, ParameterizedTypeReference<E> entityClass) {
        super(context, path);
        this.uriBuilder = getContext().getUriBuilder();
        this.uriBuilder.path(resolvePath());
        this.entityClass = entityClass;
    }

    protected RestUriBuilder getUriBuilder() {
        return this.uriBuilder;
    }

    public E get() throws IOException, URISyntaxException {
        return getContext().execute(uriBuilder, entityClass);
    }
}
