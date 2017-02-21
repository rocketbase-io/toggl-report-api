package io.rocketbase.toggl.api;

public class TogglReportApi {

    private final RequestContext context;

    public TogglReportApi(RequestContext context) {
        this.context = context;
    }

    public GetDetailed detailed() {
        return new GetDetailed(context);
    }

}
