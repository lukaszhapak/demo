package com.example.demo.nonspring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ArgumentCaptorTest {

  final ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
  final StudentRepository studentRepository = mock(StudentRepository.class);
  final StudentService studentService = new StudentService(studentRepository);

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = new Student();
	student.setName("John");
	student.setAge(24);

	// when
	studentService.save(student);

	// then
	verify(studentRepository).save(studentArgumentCaptor.capture());
	Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();

	assertThat(studentArgumentCaptorValue.getName()).isEqualTo("John"); // comparing fields one by one
	assertThat(studentArgumentCaptorValue.getAge()).isEqualTo(24);

	assertThat(studentArgumentCaptorValue).usingRecursiveComparison().isEqualTo(student); // or all at once using recursive comparison
  }
}