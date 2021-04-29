package dev.samsanders.study.springprofessional.data;

import java.time.Instant;
import java.util.Objects;

public class ExampleDomainObject {

	private final long id;
	private final String content;
	private final Instant createdInstant;

	public ExampleDomainObject(long id, String content, Instant createdInstant) {
		this.id = id;
		this.content = content;
		this.createdInstant = createdInstant;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	Instant getCreatedInstant() {
		return createdInstant;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ExampleDomainObject)) return false;
		ExampleDomainObject that = (ExampleDomainObject) o;
		return id == that.id &&
				Objects.equals(content, that.content) &&
				Objects.equals(createdInstant, that.createdInstant);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, createdInstant);
	}
}
