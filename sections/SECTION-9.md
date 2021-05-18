#   Section 9 – Spring Boot Basics

*   What is Spring Boot?

    Spring Boot helps developers create stand-alone, production-grade Spring-based applications. Spring Boot takes an
    opinionated view of the Spring platform and third-party libraries to help application developers get started
    building business applications with reasonable defaults that can be easily overridden.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started

*   What are the advantages of using Spring Boot?

    *   Provide a faster and accessible getting-started experience for all Spring development
    *   Be opinionated out of the box, and get out of the way quickly as requirements diverge from defaults
    *   Provide a range of non-functional features common to large classes of projects (embedded web servers, security,
        metrics, health checks, externalized configuration)
    *   No code generation and no XML configuration

*   What things affect what Spring Boot sets up?

    Spring Boot tries to guess how you want to configure Spring, and what reasonable defaults to implement based on
    jar dependencies (i.e.: the classpath). For example, adding `spring-boot-starter-web` dependency assumes you are
    making a web application, and will configure some reasonble defaults accordingly.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-auto-configuration

*   What is a Spring Boot starter? Why is it useful?

    A starter is a POM project (i.e.: no code, just a manifest of dependencies) that can be declared as a dependency to
    import a collection of dependencies that together provide an excellent starting point for a specific technology.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-first-application-dependencies

*   Spring Boot supports both properties and YML files. Would you recognize and understand them if you saw them?

    Yes.

*   Can you control logging with Spring Boot? How?

    Yes, Spring Boot uses Commons Logging, with Logback as the default log implementation. The levels that get streamed
    can be controlled on a per-package basis using external configuration under the `logging.level.<package>` key.

    More can be controlled as well, see documentation for more.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-logging

*   Where does Spring Boot look for application.properties file by default?

    Spring Boot will look through the following locations for an `application.properties` or `.yml` file when the
    application starts:

    *   the classpath root
    *   the classpath `/config` package
    *   the current directory
    *   the `/config` subdirectory in the current directory
    *   immediate child directories of the `/config` subdirectory

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-files

*   How do you define profile specific property files?

    Add a `-{profilename}` to the property file name (without curly braces).

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-files-profile-specific

*   How do you access the properties defined in the property files?

    > Documents from the loaded files are added as PropertySources to the Spring Environment

    Multiple ways, including:
    *   JavaBean properties binding using @ConfigurationProperties and a POJO to represent the properties
    *   Constructor binding
    *   @Value

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-typesafe-configuration-properties

*   What properties do you have to define in order to configure external MySQL?

    `spring.datasource.url`, `.username`, `.password`

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-connect-to-production-database

*   How do you configure default schema and initial data?

    Spring Boot will initialize a database in different ways depending on the stack. JPA will generate DDL based on
    external configuration. Otherwise the schema can be initialized by a file named `schema.sql` and initial data
    imported by a file named `data.sql` in standard root classpath locations.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-database-initialization

*   What is a fat jar? How is it different from the original jar?

    The "original jar" (more like "war") is not executable, and needs an application container to run it. The "fat" jar
    that Spring Boot builds by default is executable, and contains an embedded server, and does not need to be run
    within an application container.

*   What embedded containers does Spring Boot support?

    *   Tomcat
    *   Jetty
    *   Undertow
    *   Reactor Netty
