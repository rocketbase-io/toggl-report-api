package io.rocketbase.toggl.api.model.weekly;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by marten on 07.03.17.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Caption {

    private String project;
    private String client;

    @JsonIgnore
    @Override
    public String getCaption() {
        return String.format("%s: %s", client, project);
    }
}
