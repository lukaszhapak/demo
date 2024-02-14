package com.example.demo.nonspring.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
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
  @DisplayName("should save student with argument matcher")
  void shouldSaveStudentWithArgumentMatcher() {
	// given
	Student student = getStudent();
	when(studentRepository.save(student)).thenReturn(getStudent()); // it requires same object reference

	// when
	Student response = studentService.save(student);

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should save student with matcher on name")
  void shouldSaveStudentWithArgumentMatcherOnName() {
	// given
	when(studentRepository.save(argThat(arg -> arg.getName().equals("John")))).thenReturn(getStudent());

	// when
	Student response = studentService.save(getStudent());

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get student by name")
  void shouldGetStudentByName() {
	// given
	when(studentRepository.getStudentByName("John")).thenReturn(getStudent());

	// when
	Student response = studentService.getStudentByName("John");

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	when(studentRepository.getStudentById(12L)).thenReturn(getStudent());

	// when
	Student response = studentService.getStudentById(12L);

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get student by name and id")
  void shouldGetStudentByNameAndId() {
	// given
	when(studentRepository.getStudentByNameAndId(any(), eq(12L))).thenReturn(getStudent());  // eq() required here because other argument uses any()

	// when
	Student response = studentService.getStudentByNameAndId("John", 12L);

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