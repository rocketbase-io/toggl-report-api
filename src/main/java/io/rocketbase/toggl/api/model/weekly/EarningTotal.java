package io.rocketbase.toggl.api.model.weekly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EarningTotal {

    private String currency;
    private List<Double> amount;
}
