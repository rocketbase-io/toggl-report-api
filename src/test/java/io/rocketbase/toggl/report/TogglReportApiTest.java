package io.rocketbase.toggl.report;

import io.rocketbase.toggl.report.model.*;
import io.rocketbase.toggl.report.util.FetchAllDetailed;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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


        List<TimeEntry> resultList = FetchAllDetailed.getAll(togglReportApi.detailed()
                .until(LocalDateTime.now())
                .since(LocalDateTime.now().minusDays(14))
                .billable(Billable.BOTH));

        assertThat(resultList, notNullValue());
    }

    @SneakyThrows
    @Test
    public void weeklyUsersTime() {
        TogglReportApi togglReportApi = getTogglReportApi();


        WeeklyUsersTimeResult result = togglReportApi.weeklyUsersTime()
                .since(LocalDateTime.now().minusDays(14))
                .get();

        assertThat(result, notNullValue());
    }

    @SneakyThrows
    @Test
    public void weeklyProjectsTime() {
        TogglReportApi togglReportApi = getTogglReportApi();

        WeeklyProjectsTimeResult result = togglReportApi.weeklyProjectsTime()
                .since(LocalDateTime.now().minusDays(14))
                .get();

        assertThat(result, notNullValue());
    }

    @SneakyThrows
    @Test
    public void weeklyUsersEarnings() {
        TogglReportApi togglReportApi = getTogglReportApi();


        WeeklyUsersEarningsResult result = togglReportApi.weeklyUsersEarnings()
                .since(LocalDateTime.now().minusDays(14))
                .get();

        assertThat(result, notNullValue());
    }


    @SneakyThrows
    @Test
    public void weeklyProjectsEarnings() {
        TogglReportApi togglReportApi = getTogglReportApi();

        WeeklyProjectsEarningsResult result = togglReportApi.weeklyProjectsEarnings()
                .since(LocalDateTime.now().minusDays(14))
                .get();

        assertThat(result, notNullValue());
    }

}
