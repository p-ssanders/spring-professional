#   Section 2 – Aspect-Oriented Programming (AOP)

*   What is the concept of AOP? Which problem does it solve? What is a cross cutting concern?

    Aspect-Oriented Programming complements Object-Oriented Programming by providing another way of thinking about
    program structure. "aspect" :: AOP as "class" :: OOP.

    It enables the modularization of concerns that cut across multiple types and objects. Transactions are the primary
    example.

*   What is a pointcut, a join point, an advice, an aspect, weaving?

    Aspect: "a modularization of a concern that cuts across multiple classes" -- I guess this means a bunch of classes
    that model a cross-cutting concern in an application. Very meta. Transaction management is the go-to example. 
    Join Point: "a point during execute of a program" but in Spring it's only ever a method execution
    Advice: "action taken by an aspect at a join point" -- The type can be "around", "before", or "after".
    Pointcut: "a predicate that matches join points" -- Specifies the methods to which advice should be applied, by
    matching using an expression language (AspectJ).
    Weaving: how AOP concepts affect production code. The choice is runtime (Spring default), or compile time (AspectJ).

*   How does Spring solve (implement) a cross cutting concern?

    By wrapping beans in proxies to apply advice. Either CGLIB proxies or JDK dynamic proxies.

*   Which are the limitations of the two proxy-types?

    Using CGLIB will require an additional dependency (on CGLIB), and CGLIB proxies can only intercept public method
    calls on non-final classes.

    Using JDK dynamic proxies does not require additional dependencies, but the class targeted for advice needs to
    implement at least one interface.

*   How many advice types does Spring support? Can you name each one?

    Before: advice that runs before a join point with no ability to skip execution of the join (i.e.: by returning or
    throwing)
    After: advice that runs after a join point completes normally
    After throwing: advice that runs after a join point completes by throwing an exception
    After finally: advice that runs always after a join point
    Around: advice that can run before and after the join point with ability to skip execution of the join point

*   If shown pointcut expressions, would you understand them?

    I'd probably have to use the reference. Is this on the test?

*   What is the JoinPoint argument used for?

    Can be declared as the first parameter of an advice method to provide access to the current join point.

*   What is a ProceedingJoinPoint? Which advice type is it used with?

    A reference to a join point. Allows an "around" advice to invoke `proceed` allowing the underlying method to run.