package io.rocketbase.toggl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginableResult<E> implements Iterable<E>, Serializable {

    private List<E> data;

    @JsonProperty("total_grand")
    private long totalGrand;

    @JsonProperty("total_billable")
    private long totalBillable;

    @JsonProperty("total_currencies")
    private List totalCurrencies;

    public Iterator<E> iterator() {
        return data.iterator();
    }

}
