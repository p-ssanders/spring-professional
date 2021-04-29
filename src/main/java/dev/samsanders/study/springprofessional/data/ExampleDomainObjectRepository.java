package dev.samsanders.study.springprofessional.data;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExampleDomainObjectRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ExampleDomainObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	ExampleDomainObject saveBroken(ExampleDomainObject exampleDomainObject) {
		jdbcTemplate.update("bad sql", Collections.emptyMap());
		return null;
	}

}
