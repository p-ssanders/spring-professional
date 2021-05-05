#   Section 7 – Security

*   What are authentication and authorization? Which must come first?

    Authentication answers the question "who are you, and why should I believe you?"

    > Authentication is how we verify the identity of who is trying to access a particular resource.

    ref: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authentication

    Authorization answers the question "what are you allowed to do?"

    Authentication must be established before authorization can be enforced, because a system must know who a client is
    before it is able to determine which resources that client can access.

*   Is security a cross cutting concern? How is it implemented internally?

    Yes. Spring Security is implemented by participating in the Servlet Filter chain by registering a class
    (DelegatingFilterProxy) as a standard Servlet Filter, which then delegates to a Spring bean that implements Filter.
    Usually this Spring bean is a FilterChainProxy which determines the correct SecurityFilterChain to invoke based on
    configuration.

*   What is the delegating filter proxy?

    DelegatingFilterProxy is a Servlet Filter that Spring Security registers using servlet container standards to bridge
    Servlet contexts to Spring Application Contexts.

    > Proxy for a standard Servlet Filter, delegating to a Spring-managed bean that implements the Filter interface

    ref: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/DelegatingFilterProxy.html 

*   What is the security filter chain?

    The SecurityFilterChain is used by the FilterChainProxy (which is used by DelegatingFilterProxy) to determine which
    Spring Security Filters should be invoked for a given request.

    > Defines a filter chain which is capable of being matched against an HttpServletRequest. in order to decide whether it applies to that request.

    ref: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/SecurityFilterChain.html

*   What is a security context?

    A SecurityContext is obtained from the SecurityContextHolder and contains the Authentication of the currently
    authenticated user.

*   What does the ** pattern in an antMatcher or mvcMatcher do?

    The ** pattern matches all sub-paths.

*   Why is the usage of mvcMatcher recommended over antMatcher?

    antMatcher compares ant-atyle patterns again the URL, excluding query string. It's a very simple regex matching
    style whereas mvcMatcher uses Spring MVC's HandlerMappingIntrospector to match paths according to Spring MVC
    matching to reduce the likelihood of security issues due to mismatches.

    E.g.: antMatchers("/some-path") only matches exactly "/some-path", whereas mvcMatchers("/some-path") matches
    "/some-path", "/some-path/", "/some-path.something" just like it would in a @RequestMapping annotation.

    ref: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/util/matcher/AntPathRequestMatcher.html
    ref: https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/servlet/util/matcher/MvcRequestMatcher.html

*   Does Spring Security support password encoding?

    Yes, the PasswordEncoder interface is provided to perform a one way transformation of a password to allow it to be
    stored securely. The default implementation is DelegatingPasswordEncoder which can delegate password encoding to
    various password encoder implementations based on the desired type indicated by a prefix in the password.

    ref: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authentication-password-storage-dpe

*   Why do you need method security? What type of object is typically secured at the method level (think of its purpose
    not its Java type).

    Method security allows developers to add security to service layer methods. Don't understand the question --
    business objects ?

*   What do @PreAuthorized and @RolesAllowed do? What is the difference between them?

    @PreAuthorize is a Spring annotation, whereas @RolesAllowed is a Java (JSR-250) annotation. @PreAuthorize supports
    SpEL expressions, whereas @RolesAllowed only supports role-based security.

*   How are these annotations implemented?

    AOP proxies are created to wrap annotated beans upon registration in the ApplicationContext.

*   In which security annotation, are you allowed to use SpEL?

    Spring annotations in the `org.springframework.security.access.prepost` package.