package com.example.demo.spring.hibernatedatatypes;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRepositoryTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save and fetch student")
  void shouldSaveAndFetchStudent() {
	// given
	Student student = createStudent();

	// when
	studentRepository.save(student);

	// then
	List<Student> all = studentRepository.findAll();
  }

  Student createStudent() {
	return Student.builder()
		.firstName("John")
		.lastName("Doe")
		.age(24)
		.grades(new int[]{2, 5, 4, 3, 2, 4})
		.address(Address.builder()
			.streetName("Street")
			.flatNumber("22")
			.build())
		.build();
  }
}