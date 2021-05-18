#   Section 6 – Spring MVC REST

*   What does REST stand for?

    REpresentational State Transfer (of Resources)

*   What is a resource?

    A thing served by a web server.

*   Is REST secure? What can you do to secure it?

    It doesn't address security. You can secure it by enforcing an authentication and authorization scheme that controls
    access to resources.

*   Is REST scalable and/or interoperable?

    Sure. Is this in the Spring docs?

*   Which HTTP methods does REST use?

    POST for creating resources, GET for reading resources, PUT (and maybe PATCH) for updating resources, and DELETE
    for deleting resources.

*   What is an HttpMessageConverter?

    An HttpMessageConverter is a strategy interface for converting from and to HTTP requests and responses.

    ref: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/HttpMessageConverter.html

*   Is @Controller a stereotype? Is @RestController a stereotype?

    Yes, and yes.

*   What is the difference between @Controller and @RestController?

    @RestController is a composed annotation of @Controller and @ResponseBody to indicate controller in which every
    method writes directly to the response body using message conversion rather than view resolution and rendering with
    an HTML template (the default behavior of @Controller).

    ref: https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-controller

*   When do you need to use @ResponseBody?

    To indicate that a method return value should be written directly as the response body.

    ref: https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html?org/springframework/http/converter/HttpMessageConverter.html

*   What are the HTTP status return codes for a successful GET, POST, PUT or DELETE operation?

    GET: 200 OK, POST: 201 Created, PUT: 204 No Content, DELETE: 204 No Content

*   When do you need to use @ResponseStatus?

    Kind of a weird annotation, but provides some convenience of ensuring a handler method returns a response with the
    specified status code, regardless of what the method tries to set as the response status.

*   Where do you need to use @ResponseBody? What about @RequestBody?

    @ResponseBody is used to annotate method return type declarations to indicate that the return value should be
    written as the actual response body using message conversion, rather than having the framework try to resolve a
    view.

    @RequestBody is used to annotate method parameter declarations to indicate that the argument should be read directly
    from the body of the request using message conversion.

*   If you saw example Controller code, would you understand what it is doing? Could you tell if it was annotated
    correctly?

    Yes in most cases. API docs are always a good resource though.

*   What Spring Boot starter would you use for a Spring REST application?

    spring-boot-starter-web

*   If you saw an example using RestTemplate, would you understand what it is doing?

    Yes in most cases. API docs are always a good resource though.