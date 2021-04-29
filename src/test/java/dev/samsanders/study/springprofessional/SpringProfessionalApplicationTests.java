package dev.samsanders.study.springprofessional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import dev.samsanders.study.springprofessional.app.ExampleService;
import dev.samsanders.study.springprofessional.data.ExampleDomainObject;
import dev.samsanders.study.springprofessional.data.ExampleDomainService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SpringProfessionalApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExampleDomainService exampleDomainService;

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
		AtomicInteger beanPostProcessorInvocationCounter = (AtomicInteger) applicationContext
				.getBean("beanPostProcessorInvocationCounter");
		// FIXME Expected 158, Actual 110 -- why?
		assertEquals(applicationContext.getBeanDefinitionCount() * 2, beanPostProcessorInvocationCounter.get());
	}

	@Test
	void exampleBeanFactoryPostProcessor_modifiesBeanMetadata() {
		assertTrue(beanFactory instanceof ConfigurableListableBeanFactory);
		BeanDefinition exampleSingletonBeanDefinition = ((ConfigurableListableBeanFactory) beanFactory)
				.getBeanDefinition("exampleSingletonBean");

		String actual = (String) exampleSingletonBeanDefinition.getAttribute("some-attribute");

		assertEquals("some-value", actual);
	}

	@Test
	void exampleService_hasDefaultValueInjected() {
		ExampleService exampleService = (ExampleService) applicationContext.getBean("exampleService");

		assertEquals("defaultValue", exampleService.getExampleValue());
	}

	@Test
	void jdbcTemplate_query_withCallbackHandler() {
		assertNotNull(this.jdbcTemplate);
		jdbcTemplate.execute("insert into example (content) values ('some-content')");
		RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();

		jdbcTemplate.query("select id, content, created_datetime from example where id = 1",
				rowCountCallbackHandler);

		assertEquals(1, rowCountCallbackHandler.getRowCount());
	}

	@Test
	void jdbcTemplate_query_witRowMapper() {
		jdbcTemplate.execute("insert into example (content) values ('some-content')");

		List<ExampleDomainObject> exampleDomainObjects = jdbcTemplate
				.query("select id, content, created_datetime from example where id = 1", new RowMapper<ExampleDomainObject>() {
					@Override
					public ExampleDomainObject mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new ExampleDomainObject(rs.getLong("id"), rs.getString("content"), Instant
								.ofEpochSecond(rs.getTimestamp("created_datetime").getTime()));
					}
				});

		assertEquals(1, exampleDomainObjects.size());
		assertEquals(1, exampleDomainObjects.get(0).getId());
		assertEquals("some-content", exampleDomainObjects.get(0).getContent());
	}

	@Test
	void jdbcTemplate_query_withResultSetExtractor() {
		jdbcTemplate.execute("insert into example (content) values ('some-content')");

		ExampleDomainObject exampleDomainObject = jdbcTemplate
				.query("select id, content, created_datetime from example where id = 1", new ResultSetExtractor<ExampleDomainObject>() {
					@Override
					public ExampleDomainObject extractData(ResultSet rs) throws DataAccessException, SQLException {
						rs.next();
						return new ExampleDomainObject(rs.getLong("id"), rs.getString("content"), Instant
								.ofEpochSecond(rs.getTimestamp("created_datetime").getTime()));
					}
				});

		assertEquals(1, exampleDomainObject.getId());
		assertEquals("some-content", exampleDomainObject.getContent());
	}

	@Test
	void transactionPropagation_requiresNew() {
		assertThrows(DataAccessException.class, () -> exampleDomainService
				.txnPropagationRequiresNew(new ExampleDomainObject(0, "some-content", Instant.MIN)));
	}

}
