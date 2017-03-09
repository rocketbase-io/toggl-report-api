package io.rocketbase.toggl.api;

public class TogglReportApi {

    private final RequestContext context;

    TogglReportApi(RequestContext context) {
        this.context = context;
    }

    public GetDetailed detailed() {
        return new GetDetailed(context);
    }

    public GetWeeklyProjectsEarnings weeklyProjectsEarnings() {
        return new GetWeeklyProjectsEarnings(context);
    }

    public GetWeeklyUsersEarnings weeklyUsersEarnings() {
        return new GetWeeklyUsersEarnings(context);
    }

    public GetWeeklyProjectsTime weeklyProjectsTime() {
        return new GetWeeklyProjectsTime(context);
    }

    public GetWeeklyUsersTime weeklyUsersTime() {
        return new GetWeeklyUsersTime(context);
    }

}
