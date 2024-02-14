package com.example.demo.nonspring.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArgumentMatchersTest {

  private final StudentRepository studentRepository = mock(StudentRepository.class);
  private final StudentService studentService = new StudentService(studentRepository);

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	studentService.save(student);

	// then
	verify(studentRepository).save(argThat(arg -> arg.getName().equals(student.getName())));
	// for many fields argument captor with recursive comparison is better
  }

  @Test
  @DisplayName("should save student with mocked repository")
  void shouldSaveStudentWithMockedRepository() {
	// given
	when(studentRepository.save(argThat(arg -> arg.getName().equals("John")))).thenReturn(getStudent());

	// when
	Student response = studentService.save(getStudent());

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  private Student getStudent() {
	return Student.builder()
		.name("John")
		.age(24)
		.build();
  }
}