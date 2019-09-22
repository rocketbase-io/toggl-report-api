package io.rocketbase.toggl.report.util;

import io.rocketbase.toggl.report.GetDetailed;
import io.rocketbase.toggl.report.model.DetailedResult;
import io.rocketbase.toggl.report.model.TimeEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * handle's paging within detailed requests
 */
public final class FetchAllDetailed {

    /**
     * this will perform the query and care's about pages
     *
     * @param detailed your configured query
     * @return total list of all TimeEntries
     */
    public static List<TimeEntry> getAll(GetDetailed detailed) {
        List<TimeEntry> result = new ArrayList<>();

        DetailedResult pagedResult;
        AtomicInteger pageStepper = new AtomicInteger(1);
        do {
            pagedResult = detailed
                    .page(pageStepper.get())
                    .get();
            result.addAll(pagedResult.getData());
        } while (pageStepper.getAndIncrement() * pagedResult.getPerPage() < pagedResult.getTotalCount()
                .intValue());

        return result;
    }
}
