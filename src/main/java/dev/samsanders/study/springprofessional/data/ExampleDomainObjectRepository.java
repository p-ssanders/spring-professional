package dev.samsanders.study.springprofessional.data;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ExampleDomainObjectRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ExampleDomainObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ExampleDomainObject doBadSql(ExampleDomainObject exampleDomainObject) {
		jdbcTemplate.update("bad sql", Collections.emptyMap());
		return exampleDomainObject;
	}
}
