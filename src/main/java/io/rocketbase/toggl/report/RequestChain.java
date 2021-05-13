package io.rocketbase.toggl.report;

import java.util.Objects;
import java.util.Optional;

class RequestChain {

    private final RequestContext context;

    private final Optional<RequestChain> parent;

    private final String path;

    public RequestChain(RequestContext context, String path) {
        Objects.nonNull(context);
        this.context = context;
        this.path = path;
        this.parent = Optional.empty();
    }

    public RequestChain(RequestChain parent, String path) {
        Objects.nonNull(parent);
        this.context = parent.getContext();
        this.parent = Optional.of(parent);
        this.path = path;
    }

    protected String resolvePath() {
        return ((parent.isPresent()) ? parent.get()
                .resolvePath() : "") + path;
    }

    protected RequestContext getContext() {
        return context;
    }
}
