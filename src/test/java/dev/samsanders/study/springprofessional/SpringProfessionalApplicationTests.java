package dev.samsanders.study.springprofessional;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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
    void exampleSingletonBeanIsSingleton() {
        Object exampleSingletonBean1 = applicationContext.getBean("exampleSingletonBean");
        Object exampleSingletonBean2 = applicationContext.getBean("exampleSingletonBean");

        assertSame(exampleSingletonBean1, exampleSingletonBean2);
    }

    @Test
    void examplePrototypeBeanIsPrototype() {
        Object examplePrototypeBean1 = applicationContext.getBean("examplePrototypeBean");
        Object examplePrototypeBean2 = applicationContext.getBean("examplePrototypeBean");

        assertNotSame(examplePrototypeBean1, examplePrototypeBean2);
    }

    @Test
    void lazyBeanIsLazilyInstantiated() {
        CountDownLatch countDownLatch = (CountDownLatch) applicationContext.getBean("countDownLatchForLazyBean");
        assertEquals(1, countDownLatch.getCount());

        applicationContext.getBean("exampleLazyInitializedBean");
        assertEquals(0, countDownLatch.getCount());
    }

    @Test
    void exampleBeanFactoryPostProcessor_modifiesBeanMetadata() {
        assertTrue(beanFactory instanceof ConfigurableListableBeanFactory);
        BeanDefinition exampleSingletonBeanDefinition = ((ConfigurableListableBeanFactory) beanFactory).getBeanDefinition("exampleSingletonBean");

        String actual = (String) exampleSingletonBeanDefinition.getAttribute("some-attribute");

        assertEquals("some-value", actual);
    }
}
