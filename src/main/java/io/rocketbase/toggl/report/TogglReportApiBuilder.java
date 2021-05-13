package io.rocketbase.toggl.report;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
public class TogglReportApiBuilder {

    public static final String TOGGLE_REPORT_API = "api.track.toggl.com/reports/api/v2";

    private String host = TOGGLE_REPORT_API;
    private String apiToken = null;
    private String userAgent = null;
    private Long workspaceId = null;
    private WorkspaceProvider workspaceProvider;
    private ThrottleProvider throttleProvider;

    public TogglReportApiBuilder apiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public TogglReportApiBuilder userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * simple way to fix workspace
     *
     * @param workspaceId look for your workspace id via UI or API
     * @return builder
     */
    public TogglReportApiBuilder workspaceId(long workspaceId) {
        this.workspaceId = workspaceId;
        return this;
    }

    /**
     * workspace provider to allow swapping ids within instance<br>
     * when both workspaceId and workspaceProvider are set provider will get used
     *
     * @param workspaceProvider class that return the current workspaceId in order to allow switching within one instance
     * @return builder
     */
    public TogglReportApiBuilder workspaceProvider(WorkspaceProvider workspaceProvider) {
        this.workspaceProvider = workspaceProvider;
        return this;
    }

    public TogglReportApiBuilder throttleProvider(ThrottleProvider throttleProvider) {
        this.throttleProvider = throttleProvider;
        return this;
    }

    public TogglReportApi build() {
        RequestContext context = new RequestContext(this);
        return new TogglReportApi(context);
    }

    boolean workspaceProviderPresent() {
        return workspaceProvider != null;
    }

    boolean throttleProviderPresent() {
        return throttleProvider != null;
    }

    /**
     * allow swapping workspaces via provider
     */
    public interface WorkspaceProvider {

        long getWorkspaceId();

    }

    /**
     * manually handle the throttling of the api<br>
     * useful when you work with JToggle and this api together with the same api-key
     */
    public interface ThrottleProvider {

        /**
         * @return milliseconds in future when a next call is allowed
         */
        long getNextCallAllowed();

        /**
         * will get called from {@link RequestContext} immediately after api is called in order to keep lastCall somewhere outside
         */
        void apiCalled();

    }

}
