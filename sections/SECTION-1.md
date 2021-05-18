#   Section 1 – Container, Dependency and IOC

*   What is dependency injection and what are the advantages of using it?

    Dependency injection is an approach to instantiation of objects whereby an external service ("container") takes on
    the responsibility of finding all the dependencies required to instantiate an object, and injects those
    dependencies into the object (via constructor, or setter methods) to instantiate it. It's clearly differentiated
    from the alternative approach whereby classes have internal knowledge of where their dependencies come from, most
    often implemented as a bunch of `new` invocations on the fields within a class combined with a default constructor.

    Dependency injection allows a class's dependencies to be defined externally, and passed into its methods when
    creating an instance. This means a class is easier to test as the dependencies can be controlled, or mocked.

    It also means that change does not necessarily propagate throughout a codebase if dependencies reference interfaces,
    whereas if dependencies were `new`'d directly within classes, then changes to implementations would necessarily
    propagate beyond the implementation itself.

*   What is an interface and what are the advantages of making use of them in Java?

    Interfaces specify inputs and outputs, not implementation. Building to interfaces allows implementations to change
    without affecting dependents.

*   What is an ApplicationContext?

    An ApplicationContext is a component that is responsible for the instantiation, configuration, and assembly of
    objects in a system. It gets the instructions of what to instantiate, configure, and assemble from configuration
    metadata.

*   How are you going to create a new instance of an ApplicationContext?

    `new ClassPathXmlApplicationContext("beans.xml");`

*   Can you describe the lifecycle of a Spring Bean in an ApplicationContext?

    The lifecycle of a bean in an ApplicationContext depends on the scope of the bean. Most common are Singleton and
    Prototype scopes.

*   How are you going to create an ApplicationContext in an integration test?

    I probably wouldn't, and would instead `@Autowire` the `ApplicationContext`.

*   What is the preferred way to close an application context? Does Spring Boot do this for you?

    Register a "shutdown hook" by invoking `registerShutdownHook()` on `ConfigurableApplicationContext`.

    Yes.

*   Are beans lazily or eagerly instantiated by default? How do you alter this behavior?

    Eagerly.
    Use @Lazy along with @Bean.

*   What is a property source? How would you use @PropertySource?

    A property source is any source of key-value pairs e.g.: operating system environment variables, a .properties file,
    etc.

*   What is a BeanFactoryPostProcessor and what is it used for? When is it invoked?

    A BeanFactoryPostProcessor is used to modify the metadata or the bean configurations themselves, not the beans or
    instantiated objects. It gets invoked before the ApplicationContext instantiates any beans. Use this if you want to
    programmatically change bean definitions.

*   What is a BeanPostProcessor and how is it different to a BeanFactoryPostProcessor? What do they do? When are they called?

    A BeanPostProcessor is used to modify beans before and after initialization, but after the container has already
    instantiated, configured, and assembled the bean. Use this if you want to programmatically change beans.

*   What does component-scanning do?

    Component scanning is the process by which Spring Framework will automatically discover and register bean
    definitions based on annotations directly on classes. This is an alternative to registering each bean manually
    with `@Bean` or with XML.

*   What is the behavior of the annotation @Autowired with regards to field injection, constructor injection and method injection?

    The @Autowired annotation can be applied to fields to inject values into those fields during instantiation of the
    bean.
    It can also be applied to constructors, but is superfluous if only one constructor is defined. The container will
    automatically resolve the dependencies and inject them into the constructor during instantiation of the bean with,
    or without, the annotation on the constructor.
    It can also be applied to arbitrary methods, meaning the values will be injected into that the annotated method
    during instantiation of the object, which is weird.

*   How does the @Qualifier annotation complement the use of @Autowired?

    When there are multiple beans of the same type in an ApplicationContext, the @Qualifier annotation can be used along
    with the @Autowired annotation to provide guidance to the ApplicationContext on which bean to inject, usually by
    providing the bean name as the value of the @Qualifier annotation.

*   What is a proxy object and what are the two different types of proxies Spring can create?

    A proxy object is a bean wrapped by another object (decorator) that exposes the same public interface as the bean,
    and can retrieve the bean from the relevant scope to delegate method calls to the bean. The use is mostly for
    inter-bean dependencies where the dependent bean has a shorter-term scope than the depending bean.

    The types are CGLIB dynamic proxies (default), and standard JDK interface-based proxies.

    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-other-injection-proxies

*   What does the @Bean annotation do?

    It's a method-level annotation that is an analog of the `<bean>` element in XML. It indicates that a method returns
    a value that should be registered as a bean in the ApplicationContext.

*   What is the default bean id if you only use @Bean? How can you override this?

    The method name. Can be overridden using the `name` attribute.

*   Why are you not allowed to annotate a final class with @Configuration?

    Because classes annotated with @Configuration are subclasses at runtime with CGLIB to dynamically add features at
    runtime like inter-bean dependencies.
    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-further-information-java-config

*   How do you configure profiles? What are possible use cases where they might be useful?

    Profiles are configured by using the `@Profile` annotation on beans or components, along with setting the active
    profile property to specify the profile under which the application should run.

    Useful for local vs production deployments, but that indicates a rift in dev/prod parity. More useful for tests in
    my experience e.g.: to avoid connecting to an external service during unit tests by providing a stub bean instead.

*   Can you use @Bean together with @Profile?

    The `@Profile` means the bean will only be loaded into the ApplicationContext if the specified profile is set to
    active, or the profile expression is evaluated to be "true" given the active profile (e.g. `!test`).

*   Can you use @Component together with @Profile?

    Sure, same as you can with @Bean.

*   How many profiles can you have?

    I don't think there is a limit.

*   How do you inject scalar/literal values into Spring beans?

    `@Value`

*   What is Spring Expression Language (SpEL for short)?

    SpEL is an "expression language" that is used for querying and manipulating object graphs at runtime.

    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions

*   What is the Environment abstraction in Spring?

    The Environment abstraction is basically an interface that represents the environment in which the application is
    running. It provides an API for Profiles and Properties.

*   Where can properties in the environment come from – there are many sources for properties – check the documentation if not sure. Spring Boot adds even more.

    JVM system properties, operating system environment variables, properties files..

*   What can you reference using SpEL?

    wut?

*   What is the difference between $ and # in @Value expressions?

    `$` is used to indicate a that the value can be obtained from a property source, meaning the annotation value is a key
    in some property map.
    `#` is used to indicate that the value of the annotation is a SPeL expression that should be evaluated.