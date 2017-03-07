# Java Client of Toggl's Reports API

This project is a Java client for the public [Toggl' Reports API](https://github.com/toggl/toggl_api_docs). 
The client is based on a fluent java api that maps the cascade of methods to the Toggl's api endpoints. For example:

## Compilation

```shell
mvn compile
```

## Dependency

Thank's to [jitpack](https://jitpack.io/#rocketbase-io/toggl-report-api) it's very easy to use current builds

Step 1. Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Step 2. Add the dependency
```xml
<dependency>
    <groupId>com.github.rocketbase-io</groupId>
    <artifactId>toggl-report-api</artifactId>
    <version>toggl-report-api-1.0.0</version>
</dependency>
```

## Usage

```java
public static void main(String[] args) {
    TogglReportApi togglReportApi = new TogglReportApiBuilder()
                    .setApiToken(API_TOKEN)
                    .setUserAgent(USER_AGENT)
                    .setWorkspaceId(WORKSPACE_ID)
                    .build();
    
    WeeklyUsersTimeResult result = togglReportApi.weeklyUsersTime()
                    .since(start)
                    .get();
    
    System.out.println(result);
}
```

Very short examples your can also find within the tests [goto tests](https://github.com/rocketbase-io/toggl-report-api/blob/master/src/test/java/io/rocketbase/toggl/api/TogglReportApiTest.java)


To use this client in a Spring application consider the following setup:

```java
@Configuration
public class TogglReportApiConfiguration {

    @Bean
    public TogglReportApi togglReportApi(@Value("${toggl-api.token}") String token, 
                    @Value("${toggl-api.user-agent}") String userAgent,
                    @Value("${toggl-api.workspace-id}") Long workspaceId) {
        return new TogglReportApiBuilder()
                       .setApiToken(token)
                       .setUserAgent(userAgent)
                       .setWorkspaceId(workspaceId)
                       .build();
    }
    
}
```

## Dependencies

This API depends on three other popular modules:

* `org.springframework` artifact `spring-web` version `4.3.5.RELEASE`

* `com.google.guava` artifact `guava` version `21.0`

* `com.fasterxml.jackson.core` artifact `jackson-databind` version `2.8.6`

* `org.apache.httpcomponents` artifact `httpclient` version `4.6`

Since all these artifacts are pretty popular take with care the possible artifact's version conflicts. If you are going
 to use this software under an Android device consider the [httpclient issues](https://hc.apache.org/httpcomponents-client-4.3.x/android-port.html).