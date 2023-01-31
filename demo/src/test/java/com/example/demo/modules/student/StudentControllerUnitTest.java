package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StudentControllerUnitTest {

  private static final long NON_EXISTING_STUDENT_ID = 30000L;
  private static final long EXISTING_STUDENT_ID = 10000L;

  private final StudentRepository studentRepository = new StudentRepositoryInMemory();
  private final StudentValidator studentValidator = new StudentValidator();
  private final StudentServiceImpl studentService = new StudentServiceImpl(studentRepository,
	  studentValidator);
  private final StudentController studentController = new StudentController(studentService);

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = EXISTING_STUDENT_ID;

	// when
	Student response = studentController.getStudentById(id);

	// then
	assertThat(response).usingRecursiveComparison().isEqualTo(getStudent());
  }

  @Test
  @DisplayName("should throw exception when student is not found")
  void shouldThrowExceptionWhenStudentIsNotFound() {
	// given
	Long id = NON_EXISTING_STUDENT_ID;

	// when
	Throwable thrown = catchThrowable(() -> studentController.getStudentById(id));

	// then
	assertThat(thrown).isInstanceOf(NotFoundException.class);
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

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5, -3, 15, 19, 101, Integer.MAX_VALUE})
  @DisplayName("should throw exception when student age is invalid")
  void shouldThrowExceptionWhenStudentAgeIsInvalid(int age) {
	// given
	Student student = getStudent();
	student.setAge(age);

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"a", "more than 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should throw exception when student name is invalid")
  void shouldThrowExceptionWhenStudentNameIsInvalid(String name) {
	// given
	Student student = getStudent();
	student.setName(name);

	// when
	Throwable thrown = catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class);
  }
}