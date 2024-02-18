package com.example.demo.spring.data.databases;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("h2-log-sql")
class H2LogSqlTest extends AbstractIntegrationTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("save and get student")
  void saveAndGetStudent() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	Student student = studentRepository.findById(id).get();

	// then
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isEqualTo(createStudent());
  }

  private Student createStudent() {
	return Student.builder()
		.age(22)
		.name("John")
		.build();
  }
}