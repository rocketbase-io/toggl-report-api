package io.rocketbase.toggl.report.model.weekly;

import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
public interface TimeDetails {

    String getId();

    Caption getTitle();

    List<Long> getTotals();
}
