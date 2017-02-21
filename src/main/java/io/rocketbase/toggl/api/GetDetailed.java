package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.DetailedResult;
import org.springframework.core.ParameterizedTypeReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDetailed extends ExecutableRequestChain<DetailedResult> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private static final String PATH = "/details";
    private static final ParameterizedTypeReference<DetailedResult> TYPE_REFERENCE = new ParameterizedTypeReference<DetailedResult>() {
    };

    public GetDetailed(RequestContext context) {
        super(context, PATH, TYPE_REFERENCE);
    }

    public GetDetailed page(int pageIndex) {
        super.getUriBuilder()
                .addParameter("page", String.valueOf(pageIndex));
        return this;
    }

    public GetDetailed since(Date since) {
        super.getUriBuilder()
                .addParameter("since", DATE_FORMAT.format(since));
        return this;
    }

    public GetDetailed until(Date until) {
        super.getUriBuilder()
                .addParameter("until", DATE_FORMAT.format(until));
        return this;
    }


}
