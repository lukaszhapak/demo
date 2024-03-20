package com.example.demo.spring.data.databases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("mysql-p6spy")
class MysqlP6SpyTest {

  @Autowired
  StudentRepository studentRepository;

  //  @Test   // requires mysql database, can be found in docker compose
  @DisplayName("save and get student")
  void saveAndGetStudent() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	Student student = studentRepository.findById(id).get();

	// then
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isEqualTo(createStudent());
  }

  Student createStudent() {
	return Student.builder()
		.age(22)
		.name("John")
		.build();
  }
}