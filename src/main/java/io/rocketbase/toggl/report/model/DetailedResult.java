package io.rocketbase.toggl.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 21.02.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedResult extends BaseResult {

    private List<TimeEntry> data;

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("per_page")
    private int perPage;

}
