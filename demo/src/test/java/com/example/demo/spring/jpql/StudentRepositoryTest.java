package com.example.demo.spring.jpql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student and fetch name")
  void shouldSaveStudentAndFetchName() {
	// Setup:
	Student john = getStudent();
	studentRepository.save(john);

	// When:
	String studentName = studentRepository.selectNameById(john.getId());

	// Then:
	assertThat(studentName).isEqualTo(john.getName());
  }

  @Test
  @DisplayName("should save students and fetch list")
  void shouldSaveStudentsAndFetchList() {
	// given
	studentRepository.save(getStudent());
	studentRepository.save(getStudent());

	// when
	List<StudentDTO> studentDTOS = studentRepository.findAllAsDTOs();

	// then
	assertThat(studentDTOS.size()).isEqualTo(2);
  }

  private Student getStudent() {
	return Student.builder()
		.age(22)
		.name("John")
		.build();
  }
}
