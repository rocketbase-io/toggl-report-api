# Java Client of Toggl's Reports API

This project is a Java client for the public [Toggl' Reports API](https://github.com/toggl/toggl_api_docs). 
The client is based on a fluent java api that maps the cascade of methods to the Toggl's api endpoints. For example:

## Compilation

```shell
mvn compile
```

## Usage

First you have to install the maven artifact locally

```shell
mvn install
```

Then add the artifact to your dependencies:

```xml
<dependencies>
    ...
    <dependency>
        <groupId>io.rocketbase.toggl</groupId>
        <artifactId>toggl-report-api</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    ...
</dependencies>
```

And then the usage is as follows:

```java
public static void main(String[] args) {
    TogglReportApi togglReportApi = new TogglReportApiBuilder()
                    .setApiToken(System.getenv("api_token"))
                    .setUserAgent(System.getenv("user_agent"))
                    .setWorkspaceId(Integer.parseInt(System.getenv("workspace_id")))
                    .build();
    
    DetailedResult detailedPaginableResult = togglReportApi.detailed()
                        .until(start)
                        .since(end)
                        .page(pageStepper.incrementAndGet())
                        .get();
    
    System.out.println(detailedPaginableResult);
}
```

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