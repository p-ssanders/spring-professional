#   Section 1-1 – Container, Dependency and IOC

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

*   What is a property source? How would you use @PropertySource?

    *TODO*
    
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

    

*   How does the @Qualifier annotation complement the use of @Autowired?
*   What is a proxy object and what are the two different types of proxies Spring can create?
*   What does the @Bean annotation do?
*   What is the default bean id if you only use @Bean? How can you override this?
*   Why are you not allowed to annotate a final class with @Configuration
*   How do you configure profiles? What are possible use cases where they might be useful?
*   Can you use @Bean together with @Profile?
*   Can you use @Component together with @Profile?
*   How many profiles can you have?
*   How do you inject scalar/literal values into Spring beans?
*   What is Spring Expression Language (SpEL for short)?
*   What is the Environment abstraction in Spring?
*   Where can properties in the environment come from – there are many sources for properties – check the documentation if not sure. Spring Boot adds even more.
*   What can you reference using SpEL?
*   What is the difference between $ and # in @Value expressions?