#   Section 8 – Testing

*   What type of tests typically use Spring?

    Integration tests, but also unit tests.

*   How can you create a shared application context in a JUnit integration test?

    Annotate the test class with @RunWith(SpringRunner.class) or @ExtendWith(SpringExtension.class) to integrate
    the Spring TestContext Framework into JUnit5's Jupiter programming model.

    ref: https://docs.spring.io/spring-framework/docs/5.3.6/javadoc-api/org/springframework/test/context/junit/jupiter/SpringExtension.html

*   When and where do you use @Transactional in testing?

    Annotating a test with @Transactional causes the test to be run within a transaction, and automatically rolled back
    after completion of the test. This annotation is not supported on test lifecycle methods (@BeforeEach, et al).
    The annotation can also be applied to the test class to run every test within a transaction.

    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-tx

*   How are mock frameworks such as Mockito or EasyMock used?

    By specifying a Runner, specifically MockitoJUnitRunner, for the tests, but otherwise just as they're always used.

*   How is @ContextConfiguration used?

    Defines class-level metadata that is used to determine how to load and configure an ApplicationContext for
    integration tests.

    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-testing-annotation-contextconfiguration

*   How does Spring Boot simplify writing tests?

    Spring Boot provides annotations and utilities through the `spring-boot-test` module, which provides core items,
    and the `spring-boot-test-autoconfigure` module which provides autoconfiguration (reasonable defaults).

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications

*   What does @SpringBootTest do? How does it interact with @SpringBootApplication and@SpringBootConfiguration?

    It provides some autoconfiguration, above what the regular Spring TestContext Framework provides:
    - SpringBootContextLoader as a default ContextLoader so no @ContextConfiguration is necessary
    - Automatically searches for @Configuration
    - Provides support for different `webEnvironment` modes including starting a running web server on a random port
    - Registers a TestRestTemplate/TestWebClient bean for use in web tests that use a running web server

    ref: https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html