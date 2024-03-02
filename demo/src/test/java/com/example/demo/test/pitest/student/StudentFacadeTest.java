package com.example.demo.test.pitest.student;

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

  MessagePublisher messagePublisher = mock(MessagePublisher.class);

  StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher);

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = new Student("John", 21);

	// when
	Student response = studentFacade.save(student);

	// then
	assertThat(response).usingRecursiveComparison().isEqualTo(student);
	verify(studentRepository).save(student);
	verify(messagePublisher).publishStudentSavedEvent(student);
  }

  @Test
  @DisplayName("should throw exception if name is too short")
  void shouldThrowExceptionIfNameIsTooShort() {
	// given
	Student student = new Student("J", 21);

	// when
	Throwable thrown = catchThrowable(() -> studentFacade.save(student));

	// then
	assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	verify(studentRepository, times(0)).save(any());
	verify(messagePublisher, times(0)).publishStudentSavedEvent(any());
  }

  @Test
  @DisplayName("should throw exception if age is too high")
  void shouldThrowExceptionIfAgeIsTooHigh() {
	// given
	Student student = new Student("John", 187);

	// when
	Throwable thrown = catchThrowable(() -> studentFacade.save(student));

	// then
	assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	verify(studentRepository, times(0)).save(any());
	verify(messagePublisher, times(0)).publishStudentSavedEvent(any());
  }
}