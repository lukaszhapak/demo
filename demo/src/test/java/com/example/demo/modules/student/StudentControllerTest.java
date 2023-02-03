package com.example.demo.modules.student;

import static com.example.demo.commons.helper.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StudentControllerTest {

  private static final long NON_EXISTING_STUDENT_ID = 3000000L;
  private static final long SAMPLE_STUDENT_ID = 1000001L;

  private final StudentRepositoryInMemory studentRepository = new StudentRepositoryInMemory();
  private final StudentController studentController = new StudentController(new StudentService(studentRepository, new StudentValidator()));

  @BeforeEach
  void setUp() {
	studentRepository.cleanData();
	studentRepository.insertSampleData();
  }

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = SAMPLE_STUDENT_ID;

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

  @ParameterizedTest
  @ValueSource(strings = {"aa", "John", "asdw", "asdwadawasdsadzxc",
	  "exactly 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should save student")
  void shouldSaveStudent(String name) {
	// given
	Student student = getStudent();
	student.setName(name);

	// when
	Student response = studentController.saveStudent(student);

	// then
	assertThat(response.getId()).isNotNull();
	assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
  }

  @Test
  @DisplayName("should save student without grades")
  void shouldSaveStudentWithoutGrades() {
	// given
	Student student = getStudent();
	student.setGrades(null);

	// when
	Student response = studentController.saveStudent(student);

	// then
	assertThat(response.getId()).isNotNull();
	assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

	Student studentEntity = studentRepository.getById(response.getId()).toDomain();
	assertThat(studentEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
  }

  @Test
  @DisplayName("should save student with empty grades list")
  void shouldSaveStudentWithEmptyGradesList() {
	// given
	Student student = getStudent();
	student.setGrades(Collections.emptyList());

	// when
	Student response = studentController.saveStudent(student);

	// then
	assertThat(response.getId()).isNotNull();
	assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

	Student studentEntity = studentRepository.getById(response.getId()).toDomain();
	assertThat(studentEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
  }

  @Test
  @DisplayName("should throw exception when student fields are invalid")
  void shouldThrowExceptionWhenStudentFieldsAreInvalid() {
	// given
	Student student = getStudent();
	student.setName("A");
	student.setAge(18);
	student.setGrades(List.of(1, 1, 7, 88, -2));

	// when
	ValidationException thrown = (ValidationException) catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class).hasMessageContaining("Student is invalid");
	assertThat(thrown.getInvalidFields()).hasSize(3).containsKeys("Name", "Age", "Grade");
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
	ValidationException thrown = (ValidationException) catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class).hasMessageContaining("Student is invalid");
	assertThat(thrown.getInvalidFields()).hasSize(1).containsKey("Age");
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
	ValidationException thrown = (ValidationException) catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class).hasMessageContaining("Student is invalid");
	assertThat(thrown.getInvalidFields()).hasSize(1).containsKey("Name");
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, -3, 15, 19, 101, Integer.MAX_VALUE})
  @DisplayName("should throw exception when student grade is invalid")
  void shouldThrowExceptionWhenStudentGradeIsInvalid(Integer grade) {
	// given
	Student student = getStudent();
	student.setGrades(List.of(4, grade, 2, 5, grade));

	// when
	ValidationException thrown = (ValidationException) catchThrowable(() -> studentController.saveStudent(student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class)
		.hasMessageContaining("Student is invalid");
	assertThat(thrown.getInvalidFields()).hasSize(1).containsKey("Grade");
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(ints = {1, 3, 5, -3, 15, 19, 101, Integer.MAX_VALUE})
  @DisplayName("should throw exception when student age is invalid during update")
  void shouldThrowExceptionWhenStudentAgeIsInvalidDuringUpdate(Integer age) {
	// given
	Long id = SAMPLE_STUDENT_ID;
	Student student = getStudent();
	student.setAge(age);

	// when
	ValidationException thrown = (ValidationException) catchThrowable(() -> studentController.updateStudent(id, student));

	// then
	assertThat(thrown).isInstanceOf(ValidationException.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"a", "more than 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should throw exception when student name is invalid during update")
  void shouldThrowExceptionWhenStudentNameIsInvalidDuringUpdate(String name) {
	// given
	Long id = SAMPLE_STUDENT_ID;
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
	Long id = SAMPLE_STUDENT_ID;

	// when
	studentController.deleteStudent(id);

	// then
	assertThat(studentRepository.existsById(SAMPLE_STUDENT_ID)).isFalse();
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
	Long id = SAMPLE_STUDENT_ID;
	Student student = getStudent();
	student.setName("Jim");

	// when
	Student response = studentController.updateStudent(id, student);

	// then
	student.setId(id);
	assertThat(response).usingRecursiveComparison().isEqualTo(student);

	Student studentEntity = studentRepository.getById(id).toDomain();
	assertThat(studentEntity).usingRecursiveComparison().isEqualTo(student);
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