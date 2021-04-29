#   Section 3 - Data Management: JDBC, Transactions

*   What is the difference between checked and unchecked exceptions?

    That's a Java thing, not Spring, but a checked exception is one that extends from `Exception`, must be declared
    by any methods that throw one the method signature's `throws` clause, and invocations of those methods must be
    handled at compile time.

    An unchecked exception is an Exception that extends from `RuntimeException` and doesn't need to be declared or
    handled at compile time.

*   How do you configure a DataSource in Spring?

    As a bean that implements `javax.sql.DataSource` or I guess a JDNI lookup, but I've never done that.

*   What is the Template design pattern and what is the JDBC template?

    The Template design pattern uses a callback approach to remove transaction lifecycle management concerns from app
    code by invoking your callbacks whenever appropriate during the transaction lifecycle, then returns the result.

    The Spring `JdbcTemplate` simplifies the use of JDBC by providing a high-level API that basically takes in SQL, and
    some way to map rows to objects, and then returns objects.

*   What is a callback? What are the JdbcTemplate callback interfaces that can be used with queries? What is each
used for? (You would not have to remember the interface names in the exam, but you should know what they
do if you see them in a code sample).

    A callback is an interface that is passed into another method as an argument, and invoked later, potentially by
    another thread.

    `RowCallbackHandler` is stateful, keeping the result state within for later inspection.
    `RowMapper` is used to map rows of a result set to objects. Invoked per-row. Stateless.
    `ResultSetExtractor` is used to map the entire result set. Should probably use `RowMapper` instead.

*   Can you execute a plain SQL statement with the JDBC template?

    Yes, e.g.: `jdbcTemplate.execute("insert into demo (content) values ('some-content')");`

*   When does the JDBC template acquire (and release) a connection, for every method called or once per
template? Why?

    JdbcTemplate is not responsible for database connections -- that responsibility is bestowed upon the `DataSource`.

*   How does the JdbcTemplate support queries? How does it return objects and lists/maps of objects?

    JdbcTemplate will accept queries as Strings, and will return objects and lists/maps of objects by invoking the
    callback it was given with the query to process the result set.

*   What is a transaction? What is the difference between a local and a global transaction?

    A transaction is a group of steps that modify the state of a resource, ideally complying to ACID.

    Local transactions are a J2EE concept that basically mean transactions without an external transaction manager. For
    example, creating and managing a JDBC connection yourself.

    Global transactions are a J2EE concept that basically mean transactions that are managed through some external
    transaction manager, like one that comes with an application server, meaning you have to use JTA.

*   Is a transaction a cross cutting concern? How is it implemented by Spring?

    Yes, with AOP proxies where advice is defined by metadata (annotations or XML). The combination results in an AOP
    proxy that uses a `TransactionInterceptor` in conjunction with a `TransactionManager` implementation to wrap
    transactional behavior around method invocations... so as a developer you don't need to litter your business code
    with transaction concerns.

*   How are you going to define a transaction in Spring?

    Declaratively. `@Transactional`.

*   Is the JDBC template able to participate in an existing transaction?

    Yes.

*   What is @EnableTransactionManagement for?

    An annotation to enable Spring's annotation-driven transaction management capability.

*   How does transaction propagation work?

    Propagation required: forces a physical transaction if no transaction exists yet or participating in an existing
    transaction. A logical transaction is created for each method, and inner transactions can affect the outer physical
    transaction.

    Propagration requires new: always uses a new physical transaction for each scope, never participating in a
    transaction from an outer scope. Commit and roll-back are independent in that an inner transaction will not affect
    the outer transaction.

*   What happens if one @Transactional annotated method is calling another @Transactional annotated method
inside a same object instance?

    Nothing really. This question would be more interesting if a non-annotated method called an @Transactional annotated
    method within the same object instance.

    In proxy-mode only external method calls that come through the proxy are intercepted. Self-invocation does not go
    through the proxy, so there is no transaction.

*   Where can the @Transactional annotation be used? What is a typical usage if you put it at class level?

    Public methods or class level. If used at class level it provides a default behavior for all methods in the class.

*   What does declarative transaction management mean?

    Declarative transaction management allows developers to specify transaction behavior with configuration,
    specifically what should happen, not how. This approach is the least invasive to code, meaning business classes do
    not need to reference or depend on transaction infrastructure.

*   What is the default rollback policy? How can you override it?

    By default rollback is automatic only on unchecked exceptions. This can be overridden by *TODO*

*   What is the default rollback policy in a JUnit test, when you use the @
RunWith(SpringJUnit4ClassRunner.class) in JUnit 4 or @ExtendWith(SpringExtension. class) in JUnit 5, and
annotate your @Test annotated method with @Transactional?

*   Are you able to participate in a given transaction in Spring while working with JPA?

    Yes.

*   Which PlatformTransactionManager(s) can you use with JPA?

*   What do you have to configure to use JPA with Spring? How does Spring Boot make this easier?