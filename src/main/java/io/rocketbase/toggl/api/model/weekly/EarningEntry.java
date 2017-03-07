package io.rocketbase.toggl.api.model.weekly;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
public interface EarningEntry {

    Caption getTitle();

    String getId();

    List<EarningTotal> getTotals();

    List<? extends EarningDetails> getDetails();
}
