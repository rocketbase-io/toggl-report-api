package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.*;
import io.rocketbase.toggl.api.model.detailed.TimeEntry;
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

    protected TogglReportApi getTogglReportApi() {
        String apiToken = "api_token";
        Long workSpaceId = 0L;
        try {
            apiToken = System.getenv("API_TOKEN");
            workSpaceId = Long.parseLong(System.getenv("WORKSPACE_ID"));
        } catch (Exception e) {
            System.err.println("System Env not set use defaults");
        }

        return new TogglReportApiBuilder()
                .setApiToken(apiToken)
                .setUserAgent("java-test")
                .setWorkspaceId(workSpaceId)
                .build();
    }


    @SneakyThrows
    @Test
    public void detailedReport() {
        TogglReportApi togglReportApi = getTogglReportApi();

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
                    .billable(Billable.NO)
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

    @SneakyThrows
    @Test
    public void weeklyUsersTime() {
        TogglReportApi togglReportApi = getTogglReportApi();

        Date start = DateTime.now()
                .minusDays(14)
                .toDate();

        WeeklyUsersTimeResult result = togglReportApi.weeklyUsersTime()
                .since(start)
                .get();

        assertThat(result, notNullValue());
    }

    @SneakyThrows
    @Test
    public void weeklyProjectsTime() {
        TogglReportApi togglReportApi = getTogglReportApi();

        Date start = DateTime.now()
                .minusDays(14)
                .toDate();

        WeeklyProjectsTimeResult result = togglReportApi.weeklyProjectsTime()
                .since(start)
                .get();

        assertThat(result, notNullValue());
    }

    @SneakyThrows
    @Test
    public void weeklyUsersEarnings() {
        TogglReportApi togglReportApi = getTogglReportApi();

        Date start = DateTime.now()
                .minusDays(14)
                .toDate();

        WeeklyUsersEarningsResult result = togglReportApi.weeklyUsersEarnings()
                .since(start)
                .get();

        assertThat(result, notNullValue());
    }


    @SneakyThrows
    @Test
    public void weeklyProjectsEarnings() {
        TogglReportApi togglReportApi = getTogglReportApi();

        Date start = DateTime.now()
                .minusDays(14)
                .toDate();

        WeeklyProjectsEarningsResult result = togglReportApi.weeklyProjectsEarnings()
                .since(start)
                .get();

        assertThat(result, notNullValue());
    }

}
