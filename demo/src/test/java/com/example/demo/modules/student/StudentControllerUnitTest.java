package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.modules.student.api.Student;
import com.example.demo.modules.student.api.StudentEndpoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StudentControllerUnitTest {

  private static final long NON_EXISTING_STUDENT_ID = 30000L;
  private static final long GET_STUDENT_ID = 1000001L;
  private static final long DELETE_STUDENT_ID = 1000002L;
  private static final long UPDATE_STUDENT_ID = 1000003L;

  private final StudentRepository studentRepository = new StudentRepositoryInMemory();
  private final StudentValidator studentValidator = new StudentValidator();
  private final StudentService studentService = new StudentService(studentRepository,
	  studentValidator);
  private final StudentEndpoint studentController = new StudentController(studentService);

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = GET_STUDENT_ID;

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
  @NullSource
  @ValueSource(ints = {1, 3, 5, -3, 15, 19, 101, Integer.MAX_VALUE})
  @DisplayName("should throw exception when student age is invalid")
  void shouldThrowExceptionWhenStudentAgeIsInvalid(Integer age) {
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

  @ParameterizedTest
  @NullSource
  @ValueSource(ints = {1, 3, 5, -3, 15, 19, 101, Integer.MAX_VALUE})
  @DisplayName("should throw exception when student age is invalid during update")
  void shouldThrowExceptionWhenStudentAgeIsInvalidDuringUpdate(Integer age) {
	// given
	Long id = UPDATE_STUDENT_ID;
	Student student = getStudent();
	student.setAge(age);

	// when
	Throwable thrown = catchThrowable(() -> studentController.updateStudent(id, student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"a", "more than 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should throw exception when student name is invalid during update")
  void shouldThrowExceptionWhenStudentNameIsInvalidDuringUpdate(String name) {
	// given
	Long id = UPDATE_STUDENT_ID;
	Student student = getStudent();
	student.setName(name);

	// when
	Throwable thrown = catchThrowable(() -> studentController.updateStudent(id, student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class);
  }

  @Test
  @DisplayName("should delete by id")
  void shouldDeleteById() {
	// given
	Long id = DELETE_STUDENT_ID;

	// when
	studentController.deleteStudent(id);

	// then
	Throwable thrown = catchThrowable(() -> studentController.getStudentById(id));
	assertThat(thrown).isInstanceOf(NotFoundException.class);
  }

  @Test
  @DisplayName("should throw not found exception when delete student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteStudentIsNotExists() {
	// given
	Long id = NON_EXISTING_STUDENT_ID;

	// when
	Throwable thrown = catchThrowable(() -> studentController.deleteStudent(id));

	// then
	assertThat(thrown).isInstanceOf(NotFoundException.class);
  }

  @Test
  @DisplayName("should update student")
  void shouldUpdateStudent() {
	// given
	Long id = UPDATE_STUDENT_ID;
	Student student = getStudent();
	student.setName("Jim");
	student.setId(id);

	// when
	Student response = studentController.updateStudent(id, student);

	// then
	assertThat(response).usingRecursiveComparison().isEqualTo(student);
  }

  @Test
  @DisplayName("should throw not found exception when update student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteUpdateIsNotExists() {
	// given
	Long id = NON_EXISTING_STUDENT_ID;
	Student student = getStudent();

	// when
	Throwable thrown = catchThrowable(() -> studentController.updateStudent(id, student));

	// then
	assertThat(thrown).isInstanceOf(NotFoundException.class);
  }

}