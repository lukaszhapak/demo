package com.example.demo.test.unit.pitest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentFacadeTest {

  StudentRepository studentRepository = mock(StudentRepository.class);

  StudentEventPublisher studentEventPublisher = mock(StudentEventPublisher.class);

  StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, studentEventPublisher);
  StudentDTO student = new StudentDTO("John", 21);

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// when
	StudentDTO response = studentFacade.save(student);

	// then
	assertThat(response).usingRecursiveComparison().isEqualTo(student);
	verify(studentRepository).save(any());
	verify(studentEventPublisher).publishStudentSavedEvent(any());
  }

  @Test
  @DisplayName("should throw exception if name is too short")
  void shouldThrowExceptionIfNameIsTooShort() {
	// given
	student.setName("J");

	// when
	Throwable thrown = catchThrowable(() -> studentFacade.save(student));

	// then
	assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	verify(studentRepository, times(0)).save(any());
	verify(studentEventPublisher, times(0)).publishStudentSavedEvent(any());
  }

  @Test
  @DisplayName("should throw exception if age is too high")
  void shouldThrowExceptionIfAgeIsTooHigh() {
	// given
	student.setAge(187);

	// when
	Throwable thrown = catchThrowable(() -> studentFacade.save(student));

	// then
	assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	verify(studentRepository, times(0)).save(any());
	verify(studentEventPublisher, times(0)).publishStudentSavedEvent(any());
  }
}