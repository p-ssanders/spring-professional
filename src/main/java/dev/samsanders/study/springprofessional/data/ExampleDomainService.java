package dev.samsanders.study.springprofessional.data;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ExampleDomainService {

	private final ExampleDomainObjectRepository exampleDomainObjectRepository;

	public ExampleDomainService(ExampleDomainObjectRepository exampleDomainObjectRepository) {
		this.exampleDomainObjectRepository = exampleDomainObjectRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ExampleDomainObject txnPropagationRequiresNew(ExampleDomainObject exampleDomainObject) {
		return exampleDomainObjectRepository.doBadSql(exampleDomainObject);
	}

}
