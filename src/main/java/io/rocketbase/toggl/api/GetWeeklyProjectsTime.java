package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.WeeklyProjectsTimeResult;
import org.springframework.core.ParameterizedTypeReference;

public class GetWeeklyProjectsTime extends AbstractBaseRequestChain<GetWeeklyProjectsTime, WeeklyProjectsTimeResult> {

    private static final ParameterizedTypeReference<WeeklyProjectsTimeResult> TYPE_REFERENCE = new ParameterizedTypeReference<WeeklyProjectsTimeResult>() {
    };

    public GetWeeklyProjectsTime(RequestContext context) {
        super(context, "/weekly", TYPE_REFERENCE);
        super.getUriBuilder()
                .addParameter("grouping", "projects");
        super.getUriBuilder()
                .addParameter("calculate", "time");
    }

}
