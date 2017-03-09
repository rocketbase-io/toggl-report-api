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
public class WeeklyUsersTimeEntry implements WeeklyTimeEntry {

    @JsonProperty("title")
    private User title;

    @JsonProperty("uid")
    private String id;

    private List<Long> totals;

    private List<ProjectTimeDetails> details;

}
