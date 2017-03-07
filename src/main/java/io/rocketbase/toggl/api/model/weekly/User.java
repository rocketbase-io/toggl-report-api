package io.rocketbase.toggl.api.model.weekly;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by marten on 07.03.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Caption {

    private String user;

    @JsonIgnore
    @Override
    public String getCaption() {
        return user;
    }
}
