package dev.samsanders.study.springprofessional;

import dev.samsanders.study.springprofessional.app.ExampleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SpringProfessionalApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BeanFactory beanFactory;

    @Test
    void contextLoads() {

    }

    @Test
    void exampleSingletonBean_isSingleton() {
        Object exampleSingletonBean1 = applicationContext.getBean("exampleSingletonBean");
        Object exampleSingletonBean2 = applicationContext.getBean("exampleSingletonBean");

        assertSame(exampleSingletonBean1, exampleSingletonBean2);
    }

    @Test
    void examplePrototypeBean_isPrototype() {
        Object examplePrototypeBean1 = applicationContext.getBean("examplePrototypeBean");
        Object examplePrototypeBean2 = applicationContext.getBean("examplePrototypeBean");

        assertNotSame(examplePrototypeBean1, examplePrototypeBean2);
    }

    @Test
    void exampleLazyBean_isLazilyInstantiated() {
        CountDownLatch countDownLatch = (CountDownLatch) applicationContext.getBean("countDownLatchForExampleLazyBean");
        assertEquals(1, countDownLatch.getCount());

        applicationContext.getBean("exampleLazyInitializedBean");
        assertEquals(0, countDownLatch.getCount());
    }

    @Test
    @Disabled
    void exampleBeanPostProcessor_postProcessesBeforeAndAfterInitialization() {
        AtomicInteger beanPostProcessorInvocationCounter = (AtomicInteger) applicationContext.getBean("beanPostProcessorInvocationCounter");
        // FIXME Expected 158, Actual 110 -- why?
        assertEquals(applicationContext.getBeanDefinitionCount() * 2, beanPostProcessorInvocationCounter.get());
    }

    @Test
    void exampleBeanFactoryPostProcessor_modifiesBeanMetadata() {
        assertTrue(beanFactory instanceof ConfigurableListableBeanFactory);
        BeanDefinition exampleSingletonBeanDefinition = ((ConfigurableListableBeanFactory) beanFactory).getBeanDefinition("exampleSingletonBean");

        String actual = (String) exampleSingletonBeanDefinition.getAttribute("some-attribute");

        assertEquals("some-value", actual);
    }

    @Test
    void exampleService_hasDefaultValueInjected() {
        ExampleService exampleService = (ExampleService) applicationContext.getBean("exampleService");

        assertEquals("defaultValue", exampleService.getExampleValue());
    }

}
