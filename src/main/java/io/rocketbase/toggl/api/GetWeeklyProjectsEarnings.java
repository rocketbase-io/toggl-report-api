package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.WeeklyProjectsEarningsResult;
import org.springframework.core.ParameterizedTypeReference;

public class GetWeeklyProjectsEarnings extends AbstractBaseRequestChain<GetWeeklyProjectsEarnings, WeeklyProjectsEarningsResult> {

    private static final ParameterizedTypeReference<WeeklyProjectsEarningsResult> TYPE_REFERENCE = new ParameterizedTypeReference<WeeklyProjectsEarningsResult>() {
    };

    public GetWeeklyProjectsEarnings(RequestContext context) {
        super(context, "/weekly", TYPE_REFERENCE);
        super.getUriBuilder()
                .addParameter("grouping", "projects");
        super.getUriBuilder()
                .addParameter("calculate", "earnings");
    }

}
