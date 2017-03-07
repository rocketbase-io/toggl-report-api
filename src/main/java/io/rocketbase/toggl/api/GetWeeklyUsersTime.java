package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.WeeklyProjectsTimeResult;
import io.rocketbase.toggl.api.model.WeeklyUsersTimeResult;
import org.springframework.core.ParameterizedTypeReference;

public class GetWeeklyUsersTime extends AbstractBaseRequestChain<GetWeeklyUsersTime, WeeklyUsersTimeResult> {

    private static final ParameterizedTypeReference<WeeklyUsersTimeResult> TYPE_REFERENCE = new ParameterizedTypeReference<WeeklyUsersTimeResult>() {
    };

    public GetWeeklyUsersTime(RequestContext context) {
        super(context, "/weekly", TYPE_REFERENCE);
        super.getUriBuilder()
                .addParameter("grouping", "users");
        super.getUriBuilder()
                .addParameter("calculate", "time");
    }

}
