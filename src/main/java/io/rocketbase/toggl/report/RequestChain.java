package io.rocketbase.toggl.report;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

class RequestChain {

    private final RequestContext context;

    private final Optional<RequestChain> parent;

    private final String path;

    public RequestChain(RequestContext context, String path) {
        Preconditions.checkNotNull(context);
        this.context = context;
        this.path = path;
        this.parent = Optional.absent();
    }

    public RequestChain(RequestChain parent, String path) {
        Preconditions.checkNotNull(parent);
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
