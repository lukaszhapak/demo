package com.example.demo.spring.hibernate;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class StudentRepositoryTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @Transactional
  @DisplayName("should save and fetch student")
  void shouldSaveAndFetchStudent() {
	// given
	Student student = createStudent();

	// when
	studentRepository.save(student);

	// then
	List<Student> all = studentRepository.findAll();
	System.out.println(all);
  }

  Student createStudent() {
	return Student.builder()
		.firstName("John")
		.lastName("Doe")
		.age(24)
		.gradesArray(new Integer[]{1,2,3,4,5,6})
		.gradesList(List.of(6,5,4,3,2,1))
		.address(Address.builder()
			.streetName("Street")
			.flatNumber("22")
			.build())
		.oneToOne(StudentOneToOne.builder()
			.name("Asd")
			.build())
			.oneToMany(List.of(
					DummyOneToMany.builder().name("32111").build(),
					DummyOneToMany.builder().name("32331").build(),
					DummyOneToMany.builder().name("32121").build()
			))
		.build();
  }
}