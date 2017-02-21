package io.rocketbase.toggl.api;

public class TogglReportApiBuilder {

    private String apiToken = null;
    private String userAgent = null;
    private Long workspaceId = null;

    public TogglReportApiBuilder setApiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }


    public TogglReportApiBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }


    public TogglReportApiBuilder setWorkspaceId(long workspaceId) {
        this.workspaceId = workspaceId;
        return this;
    }

    public TogglReportApi build() {
        RequestContext context = new RequestContext("toggl.com/reports/api/v2", apiToken, userAgent, workspaceId);
        return new TogglReportApi(context);
    }
}
