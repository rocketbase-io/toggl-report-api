package io.rocketbase.toggl.api;

import io.rocketbase.toggl.api.model.*;
import io.rocketbase.toggl.api.util.FetchAllDetailed;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;
import java.util.List;

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
                .apiToken(apiToken)
                .userAgent("java-test")
                .workspaceId(workSpaceId)
                .build();
    }


    @SneakyThrows
    @Test
    public void detailedReport() {
        TogglReportApi togglReportApi = getTogglReportApi();

        Date start = DateTime.now()
                .toDate();
        Date end = DateTime.now()
                .minusDays(20)
                .toDate();

        List<TimeEntry> resultList = FetchAllDetailed.getAll(togglReportApi.detailed()
                .until(start)
                .since(end)
                .billable(Billable.BOTH));

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
