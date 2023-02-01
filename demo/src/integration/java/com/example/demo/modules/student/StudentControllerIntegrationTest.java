package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.commons.JdbcTestHelper;
import com.example.demo.modules.student.api.Student;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  private final String URL = "/api/student/";

  private static JdbcTestHelper<Student> jdbcTestHelper;

  private static final long NON_EXISTING_STUDENT_ID = 3000000L;
  private static final long GET_STUDENT_ID = 1000001L;
  private static final long DELETE_STUDENT_ID = 1000002L;
  private static final long UPDATE_STUDENT_ID = 1000003L;

  @BeforeEach
  void setUp() {
	jdbcTestHelper = new JdbcTestHelper<>(jdbc);
  }

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = GET_STUDENT_ID;

	// when
	Response response = getHttpCall(URL + id);
	Student studentResponse = response.as(Student.class);

	//then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(getStudent());
  }

  @Test
  @DisplayName("should get not found status when student do not exists")
  void shouldGetNotFoundStatusWhenStudentDoNotExists() {
	// given
	Long id = NON_EXISTING_STUDENT_ID;

	// when
	Response response = getHttpCall(URL + id);

	//then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	Response response = postHttpCall(student, URL);
	Student studentResponse = response.as(Student.class);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	assertThat(studentResponse.getId()).isNotNull();
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

	List<Student> studentEntities = jdbcTestHelper.fetchEntities("STUDENT", Student.class);
	assertThat(studentEntities.size()).isEqualTo(1);
	assertThat(studentEntities.get(0)).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(student);
  }

  @Test
  @DisplayName("should save student without grades")
  void shouldSaveStudentWithoutGrades() {
	// given
	Student student = getStudent();
	student.setGrades(null);

	// when
	Response response = postHttpCall(student, URL);
	Student studentResponse = response.as(Student.class);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	assertThat(studentResponse.getId()).isNotNull();
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

	List<Student> studentEntities = jdbcTestHelper.fetchEntities("STUDENT", Student.class);
	assertThat(studentEntities.size()).isEqualTo(1);
	assertThat(studentEntities.get(0)).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(student);
  }

  @Test
  @DisplayName("should throw exception when student is invalid")
  void shouldThrowExceptionWhenStudentIsInvalid() {
	// given
	Student student = getStudent();
	student.setAge(2);
	student.setName("2");

	// when
	Response response = postHttpCall(student, URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
	List<Student> studentEntities = jdbcTestHelper.fetchEntities("STUDENT", Student.class);
	assertThat(studentEntities.size()).isEqualTo(0);
  }

  @Test
  @DisplayName("should delete by id")
  void shouldDeleteById() {
	// given
	Long id = DELETE_STUDENT_ID;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	boolean existsById = jdbcTestHelper.existsById(id, "STUDENT");
	assertThat(existsById).isFalse();
  }

  @Test
  @DisplayName("should throw not found exception when delete student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteStudentIsNotExists() {
	// given
	long id = NON_EXISTING_STUDENT_ID;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("should update student")
  void shouldUpdateStudent() {
	// given
	long id = UPDATE_STUDENT_ID;
	Student student = getStudent();
	student.setName("Jim");
	student.setId(id);

	// when
	Response response = putHttpCall(student, URL + id);
	Student studentResponse = response.as(Student.class);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().isEqualTo(student);

	Student studentEntity = jdbcTestHelper.fetchEntity(id, "STUDENT", Student.class);
	assertThat(studentEntity).usingRecursiveComparison().isEqualTo(student);
  }

  @Test
  @DisplayName("should throw not found exception when update student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteUpdateIsNotExists() {
	// given
	long id = NON_EXISTING_STUDENT_ID;
	Student student = getStudent();

	// when
	Response response = putHttpCall(student, URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("should throw not found exception when update student is invalid")
  void shouldThrowNotFoundExceptionWhenDeleteUpdateIsInvalid() {
	// given
	long id = UPDATE_STUDENT_ID;
	Student student = getStudent();
	student.setAge(2);
	student.setName("2");

	// when
	Response response = putHttpCall(student, URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
  }
}
