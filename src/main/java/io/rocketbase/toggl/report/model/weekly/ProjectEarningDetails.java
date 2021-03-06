package io.rocketbase.toggl.report.model.weekly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectEarningDetails implements EarningDetails {

    @JsonProperty("pid")
    private String id;

    private Project title;

    private List<EarningTotal> totals;

}
