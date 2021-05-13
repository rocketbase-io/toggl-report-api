package io.rocketbase.toggl.report.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant end;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updated;

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
