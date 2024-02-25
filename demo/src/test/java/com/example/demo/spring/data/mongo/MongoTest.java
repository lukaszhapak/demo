package com.example.demo.spring.data.mongo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("mongo")
class MongoTest extends AbstractIntegrationTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	studentRepository.save(student);

	// then
	assertThat(studentRepository.findById(student.getId()).get()).usingRecursiveComparison().isEqualTo(student);
  }

  Student getStudent() {
	return Student.builder()
		.id(UUID.randomUUID().toString())
		.name("John")
		.age(24)
		.build();
  }

}