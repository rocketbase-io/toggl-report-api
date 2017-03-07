package io.rocketbase.toggl.api.model.detailed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEntry implements Serializable {

    private Long id;

    @JsonProperty("pid")
    private Long projectId;

    @JsonProperty("tid")
    private Long taskId;

    @JsonProperty("uid")
    private Long userId;

    private String description;

    private DateTime start;

    private DateTime end;

    private DateTime updated;

    /**
     * duration in milliseconds
     */
    @JsonProperty("dur")
    private Long duration;

    private String user;

    /**
     * if the stop time is saved on the time entry, depends on user's personal settings
     */
    @JsonProperty("use_stop")
    private Boolean useStop;

    private String client;

    private String project;

    @JsonProperty("project_color")
    private String projectColor;

    private String task;

    @JsonProperty("billable")
    private Long billableAmount;

    @JsonProperty("is_billable")
    private Boolean billable;

    @JsonProperty("cur")
    private String currency;

    private List<String> tags;
}
