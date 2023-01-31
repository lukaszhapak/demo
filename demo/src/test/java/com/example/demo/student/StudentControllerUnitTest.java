package com.example.demo.student;

import static com.example.demo.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentControllerUnitTest {

  private final StudentRepository studentRepository = new StudentRepositoryInMemory();
  private final StudentValidator studentValidator = new StudentValidator();
  private final StudentServiceImpl studentService = new StudentServiceImpl(studentRepository,
	  studentValidator);
  private final StudentController studentController = new StudentController(studentService);

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = 10000L;

	// when
	Student student = studentController.getStudentById(id);

	// then
	assertThat(student).usingRecursiveComparison().isEqualTo(getStudent());
  }

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	Student response = studentController.saveStudent(student);

	// then
	assertThat(response.getId()).isNotNull();
	assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
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

  @Test
  @DisplayName("should throw exception when student name is too short")
  void shouldThrowExceptionWhenStudentNameIsTooShort() {
	// given
	Student student = getStudent();
	student.setName("a");

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("should throw exception when student name is too long")
  void shouldThrowExceptionWhenStudentNameIsTooLong() {
	// given
	Student student = getStudent();
	student.setName("65characterslongnameaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(RuntimeException.class);
  }
}