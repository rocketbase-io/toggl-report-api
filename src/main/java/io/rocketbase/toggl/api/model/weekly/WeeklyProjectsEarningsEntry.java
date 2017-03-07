package io.rocketbase.toggl.api.model.weekly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyProjectsEarningsEntry implements EarningEntry {

    @JsonProperty("title")
    private Project title;

    @JsonProperty("uid")
    private String id;

    private List<EarningTotal> totals;

    private List<UserEarningDetails> details;

}
