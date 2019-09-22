package io.rocketbase.toggl.report;

import io.rocketbase.toggl.report.model.WeeklyUsersEarningsResult;
import org.springframework.core.ParameterizedTypeReference;

public class GetWeeklyUsersEarnings extends AbstractBaseRequestChain<GetWeeklyUsersEarnings, WeeklyUsersEarningsResult> {

    private static final ParameterizedTypeReference<WeeklyUsersEarningsResult> TYPE_REFERENCE = new ParameterizedTypeReference<WeeklyUsersEarningsResult>() {
    };

    public GetWeeklyUsersEarnings(RequestContext context) {
        super(context, "/weekly", TYPE_REFERENCE);
        super.getUriBuilder()
                .addParameter("grouping", "users");
        super.getUriBuilder()
                .addParameter("calculate", "earnings");
    }

}
