package dev.samsanders.study.springprofessional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringProfessionalApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

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

}
