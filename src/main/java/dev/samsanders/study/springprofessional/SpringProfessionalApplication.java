package dev.samsanders.study.springprofessional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import dev.samsanders.study.springprofessional.app.ExampleBeanFactoryPostProcessor;
import dev.samsanders.study.springprofessional.app.ExampleBeanPostProcessor;
import dev.samsanders.study.springprofessional.app.ExampleLazyInitializedBean;
import dev.samsanders.study.springprofessional.app.ExampleLifecycleBean;
import dev.samsanders.study.springprofessional.app.ExamplePrototypeBean;
import dev.samsanders.study.springprofessional.app.ExampleSingletonBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringProfessionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProfessionalApplication.class, args);
	}

	@Bean
	@Scope("singleton")
	ExampleSingletonBean exampleSingletonBean() {
		return new ExampleSingletonBean();
	}

	@Bean
	@Scope("prototype")
	ExamplePrototypeBean examplePrototypeBean() {
		return new ExamplePrototypeBean();
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	ExampleLifecycleBean exampleLifecycleBean() {
		return new ExampleLifecycleBean();
	}

	@Bean
	ExampleBeanPostProcessor exampleBeanPostProcessor(AtomicInteger beanPostProcessorInvocationCounter) {
		return new ExampleBeanPostProcessor(beanPostProcessorInvocationCounter);
	}

	@Bean
	AtomicInteger beanPostProcessorInvocationCounter() {
		return new AtomicInteger();
	}

	@Bean
	static ExampleBeanFactoryPostProcessor exampleBeanFactoryPostProcessor() {
		return new ExampleBeanFactoryPostProcessor();
	}

	@Bean(initMethod = "init")
	@Lazy
	ExampleLazyInitializedBean exampleLazyInitializedBean(CountDownLatch countDownLatchForLazyBean) {
		return new ExampleLazyInitializedBean(countDownLatchForLazyBean);
	}

	@Bean
	CountDownLatch countDownLatchForLazyBean() {
		return new CountDownLatch(1);
	}

}
