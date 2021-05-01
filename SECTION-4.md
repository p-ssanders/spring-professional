#   Section 4 – Spring Data JPA

*   What is a Spring Data Repository interface?

    A Spring Data Repository interface is an abstraction to significantly reduce boilerplate code required to implement
    data access layers.

*   How do you define a Spring Data Repository interface? Why is it an interface not a class?

    Create an interface that extends from either `Repository` or `CrudRepository` (or any sub-interfaces), typed by
    the domain class (and it's identifier type) that the repository will support.

    Then declare method signatures with convention-based names.
    It's an interface because Spring Data JPA will process the interface as a definition, using conventions to create an
    implementation as a bean during ApplicationContext load time.

*   What is the naming convention for finder methods in a Spring Data Repository interface?

    `findBy`

*   How are Spring Data repositories implemented by Spring at runtime?

    A JpaRepositoryFactory returns an instance of JpaRepositoryImplementation based on the RepositoryInformation.

*   What is @Query used for?

    @Query is used for defining a query manually, rather than by the convention-based method name approach.

    > Although getting a query derived from the method name is quite convenient, one might face the situation in which either the method name parser does not support the keyword one wants to use or the method name would get unnecessarily ugly.

    ref: https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.declared-queries