package dev.samsanders.study.springprofessional;

import dev.samsanders.study.springprofessional.app.ExampleLifecycleBean;
import dev.samsanders.study.springprofessional.app.ExamplePrototypeBean;
import dev.samsanders.study.springprofessional.app.ExampleSingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

}
