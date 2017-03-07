package io.rocketbase.toggl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.rocketbase.toggl.api.model.weekly.EarningTotal;
import io.rocketbase.toggl.api.model.weekly.WeeklyUsersEarningsEntry;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 21.02.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyUsersEarningsResult extends BaseResult {

    @JsonProperty("week_totals")
    private List<EarningTotal> weekTotals;

    private List<WeeklyUsersEarningsEntry> data;

}
