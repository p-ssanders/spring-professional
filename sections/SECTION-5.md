#   Section 5 – Spring MVC Basics

*   What is the @Controller annotation used for?

    To mark a class for detection by classpath scanning for registration as a bean in the ApplicationContext.
    A specialization of `@Component`, it indicates the class is a web controller.

*   How is an incoming request mapped to a controller and mapped to a method?

    Methods in controller classes are mapped to HTTP requests by using `@RequestMapping` annotations.

    ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-spring-mvc

*   What is the difference between @RequestMapping and @GetMapping?

    @GetMapping is a composed annotation that composes a specific definition of @RequestMapping specific to only GET
    requests.

*   What is @RequestParam used for?

    @RequestParam is used to bind query parameters or form data within an HTTP request to a method argument.

*   What are the differences between @RequestParam and @PathVariable?

    @RequestParam maps to request parameters in the body of the request, whereas @PathVariable maps to URI variables.

*   What are the ready-to-use argument types you can use in a controller method?

    *   WebRequest
    *   javax.servlet.ServletRequest
    *   javax.servlet.ServletResponse
    *   java.security.Principal
    *   UriComponentsBuilder

*   What are some of the valid return types of a controller method?

    *   String
    *   ResponseEntity<T>
    *   HttpHeaders
    *   void