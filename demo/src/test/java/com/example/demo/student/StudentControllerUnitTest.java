package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentControllerUnitTest implements StudentControllerTest {

  private final StudentRepository studentRepository = new StudentRepositoryImpl();
  private final StudentValidator studentValidator = new StudentValidator();
  private final StudentService studentService = new StudentService(studentRepository,
	  studentValidator);
  private final StudentController studentController = new StudentController(studentService);

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = 100L;

	// when
	Student student = studentController.getStudentById(id);

	// then
	assertThat(studentsAreEqual(student, getStudent())).isTrue();
  }

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	Student response = studentController.saveStudent(student);

	// then
	assertThat(studentsAreEqual(student, response)).isTrue();
  }

  @Test
  @DisplayName("should throw exception when student is too old")
  void shouldThrowExceptionWhenStudentIsTooOld() {
	// given
	Student student = getStudent();
	student.setAge(122);

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("should throw exception when student is too young")
  void shouldThrowExceptionWhenStudentIsTooYoung() {
	// given
	Student student = getStudent();
	student.setAge(12);

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(RuntimeException.class);
  }
}