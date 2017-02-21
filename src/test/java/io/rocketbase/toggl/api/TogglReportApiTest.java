package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.DetailedResult;
import io.rocketbase.toggl.api.model.TimeEntry;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by marten on 19.01.17.
 */
public class TogglReportApiTest {

    @SneakyThrows
    @Test
    public void simpleTest() {
        TogglReportApi togglReportApi = new TogglReportApiBuilder()
                .setApiToken(System.getenv("api_token"))
                .setUserAgent(System.getenv("user_agent"))
                .setWorkspaceId(Integer.parseInt(System.getenv("workspace_id")))
                .build();

        Date start = DateTime.now()
                .toDate();
        Date end = DateTime.now()
                .minusDays(10)
                .toDate();

        DetailedResult detailedPaginableResult;
        List<TimeEntry> resultList = new ArrayList<>();

        int perPage = 50;
        int totalCount = 0;
        AtomicInteger pageStepper = new AtomicInteger();
        do {
            detailedPaginableResult = togglReportApi.detailed()
                    .until(start)
                    .since(end)
                    .page(pageStepper.incrementAndGet())
                    .get();
            resultList.addAll(detailedPaginableResult.getData());
            if (detailedPaginableResult.getTotalCount() != null) {
                totalCount = detailedPaginableResult.getTotalCount()
                        .intValue();
            }
        } while (pageStepper.get() * perPage < totalCount);


        assertThat(resultList, notNullValue());


    }
}
