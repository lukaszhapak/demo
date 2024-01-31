package com.example.demo.nonspring.argumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class StudentServiceImplTest {

  private final ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
  private final StudentRepository studentRepository = mock(StudentRepository.class);
  private final StudentService studentService = new StudentService(studentRepository);

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
	verify(studentRepository, times(1)).save(studentArgumentCaptor.capture());
	Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();
	assertThat(studentArgumentCaptorValue.getName()).isEqualTo("John");
	assertThat(studentArgumentCaptorValue.getAge()).isEqualTo(24);
  }
}