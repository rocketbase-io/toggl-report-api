package io.rocketbase.toggl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.rocketbase.toggl.api.model.weekly.WeeklyProjectsTimeEntry;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 21.02.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyProjectsTimeResult extends BaseResult {

    @JsonProperty("week_totals")
    private List<Long> weekTotals;

    private List<WeeklyProjectsTimeEntry> data;

}
