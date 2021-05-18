#   Section 10 – Spring Boot Auto Configuration

*   How does Spring Boot know what to configure?

    Spring Boot uses auto-configuration to know what to configure, as well as the beans provided by user-defined
    configuration. It uses conditions to determine when autoconfiguration should apply.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-auto-configuration
    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-understanding-auto-configured-beans

*   What does @EnableAutoConfiguration do?

    This annotation tells Spring Boot to "guess" how a user wants to configure Spring based on the dependencies added.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-first-application-auto-configuration

*   What does @SpringBootApplication do?

    This annotation is a composed annotation that composes:
    *   @EnableAutoConfiguration
    *   @ComponentScan
    *   @Configuration

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-using-springbootapplication-annotation

*   Does Spring Boot do component scanning? Where does it look by default?

    Yes, it searches starting from the package in which the class annotated with @SpringBootApplication is located.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-structuring-your-code

*   How are DataSource and JdbcTemplate auto-configured?

    Spring Boot will configure an embedded database given some embedded database technology is declared as a dependency.
    Spring Boot will otherwise use the `spring.datasource.*` properties to figure out which DataSource to create.

*   What is spring.factories file for?

    META-INF/spring.factories contains a list of classes that are candidates for autoconfiguration.

*   How do you customize Spring Boot auto configuration?

    Add a META-INF/spring.factories file, and declare a mapping from `org.springframework.boot.autoconfigure.EnableAutoConfiguration`
    to your configuration classes.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-structuring-your-code

*   What are the examples of @Conditional annotations? How are they used?

    Class (@ConditionalOnClass), bean (@ConditionalOnMissingBean), property (@ConditionalOnProperty), resource
    (@ConditionalOnProperty) conditions.

    They're used by checking for the presence of, or lack of presence of something (a class, a bean, a property, a
    resource) to determine if certain configuration should be applied.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-condition-annotations