package io.rocketbase.toggl.report.model.weekly;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
public interface WeeklyTimeEntry {

    Caption getTitle();

    String getId();

    List<Long> getTotals();

    List<? extends TimeDetails> getDetails();
}
