package dev.samsanders.study.springprofessional.data;

import org.springframework.transaction.annotation.Transactional;

public class ExampleDomainService {

	private final ExampleDomainObjectRepository exampleDomainObjectRepository;

	public ExampleDomainService(ExampleDomainObjectRepository exampleDomainObjectRepository) {
		this.exampleDomainObjectRepository = exampleDomainObjectRepository;
	}

	@Transactional
	public ExampleDomainObject brokenCreateExampleDomainObject(ExampleDomainObject exampleDomainObject) {
		return exampleDomainObjectRepository.saveBroken(exampleDomainObject);
	}

}
