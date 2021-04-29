package dev.samsanders.study.springprofessional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import dev.samsanders.study.springprofessional.app.ExampleBeanFactoryPostProcessor;
import dev.samsanders.study.springprofessional.app.ExampleBeanPostProcessor;
import dev.samsanders.study.springprofessional.app.ExampleLazyInitializedBean;
import dev.samsanders.study.springprofessional.app.ExampleLifecycleBean;
import dev.samsanders.study.springprofessional.app.ExamplePrototypeBean;
import dev.samsanders.study.springprofessional.app.ExampleService;
import dev.samsanders.study.springprofessional.app.ExampleSingletonBean;
import dev.samsanders.study.springprofessional.data.ExampleDomainObjectRepository;
import dev.samsanders.study.springprofessional.data.ExampleDomainService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
	ExampleLazyInitializedBean exampleLazyInitializedBean(CountDownLatch countDownLatchForExampleLazyBean) {
		return new ExampleLazyInitializedBean(countDownLatchForExampleLazyBean);
	}

	@Bean
	CountDownLatch countDownLatchForExampleLazyBean() {
		return new CountDownLatch(1);
	}

	@Bean
	ExampleService exampleService(@Value("${example-service.example-value:defaultValue}") String exampleValue) {
		return new ExampleService(exampleValue);
	}

	@Bean
	ExampleDomainService exampleDomainService(ExampleDomainObjectRepository exampleDomainObjectRepository) {
		return new ExampleDomainService(exampleDomainObjectRepository);
	}

}
