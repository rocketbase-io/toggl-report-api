package io.rocketbase.toggl.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
@Data
public class BaseResult {

    @JsonProperty("total_grand")
    private long totalGrand;

    @JsonProperty("total_billable")
    private long totalBillable;

    @JsonProperty("total_currencies")
    private List<CurrencyValue> totalCurrencies;
}
