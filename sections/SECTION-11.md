#   Section 11 – Spring Boot Actuator

*   What value does Spring Boot Actuator provide?

    Spring Boot Actuator includes a number of features to help monitor and manage an application in production.

    https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator

*   What are the two protocols you can use to access actuator endpoints?

    JMX, HTTP

*   What are the actuator endpoints that are provided out of the box?

    A lot: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints

*   What is info endpoint for? How do you supply data?

    `info` endpoint is used to display "arbitrary" application info. By default it doesn't display anything, but it can
    be configured to display something by creating beans of type InfoContributor, or by the presence of files on the
    classpath such as build-info.properties, and git.properties combined with properties to configure visibility.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.info

*   How do you change logging level of a package using loggers endpoint?

    Make a POST request to it with a partial entity (`configuredLevel`).

    ref: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.loggers.configure

*   How do you access an endpoint using a tag?

    Not sure?

*   What is metrics for?

    Metrics are for monitoring your application by exposing measurements.

*   How do you create a custom metric?

    Inject `MeterRegistry` into a component, and invoke the `gauge` method providing a name and a source.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics.registering-custom

*   What is Health Indicator?

    A Health Indicator provides the status of a running application, used by monitoring software to alert someone when
    a production system goes down.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health

*   What are the Health Indicators that are provided out of the box?

    A lot.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health.auto-configured-health-indicators

*   What is the Health Indicator status?

    A HealthIndicator provides actual health information, including a "Status" The Status of each indicator is
    aggregated to determine the overall health status.

*   What are the Health Indicator statuses that are provided out of the box?

    UP, DOWN, UNKNOWN, OUT_OF_SERVICE

    ref: https://github.com/spring-projects/spring-boot/blob/v2.5.0/spring-boot-project/spring-boot-actuator/src/main/java/org/springframework/boot/actuate/health/Status.java

*   How do you change the Health Indicator status severity order?

    Set the property `management.endpoint.health.status.order` to a list with the new order

*   Why do you want to leverage 3rd-party external monitoring system?

    To alert someone when a production system goes down or is at risk of going down.