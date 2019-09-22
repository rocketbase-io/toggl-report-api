package io.rocketbase.toggl.report;

import io.rocketbase.toggl.report.model.DetailedResult;
import org.springframework.core.ParameterizedTypeReference;

public class GetDetailed extends AbstractBaseRequestChain<GetDetailed, DetailedResult> {

    private static final ParameterizedTypeReference<DetailedResult> TYPE_REFERENCE = new ParameterizedTypeReference<DetailedResult>() {
    };

    public GetDetailed(RequestContext context) {
        super(context, "/details", TYPE_REFERENCE);
    }

    public GetDetailed page(int pageIndex) {
        super.getUriBuilder()
                .addParameter("page", String.valueOf(pageIndex));
        return this;
    }


}
