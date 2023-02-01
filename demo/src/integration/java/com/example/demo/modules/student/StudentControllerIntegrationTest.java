package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.modules.student.api.Student;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  public static final String URL = "/api/student/";

  @AfterEach
  void tearDown() {
	// clean up
	jdbc.update("DELETE FROM STUDENT", new MapSqlParameterSource());
  }

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	jdbc.update(
		"INSERT INTO STUDENT (ID, NAME, AGE, GRADES) VALUES (1000001, 'John', 22, '2,5,4,3,3');",
		new MapSqlParameterSource());
	long id = 1000001L;

	// when
	Response response = getHttpCall(URL + id);
	Student studentResponse = response.as(Student.class);

	//then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(getStudent());

	// clean up
	jdbc.update("DELETE FROM STUDENT", new MapSqlParameterSource());
  }

  @Test
  @DisplayName("should get not found status when student do not exists")
  void shouldGetNotFoundStatusWhenStudentDoNotExists() {
	// given
	long id = 3000000L;

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

	List<Student> studentEntities = fetchStudentEntities();
	assertThat(studentEntities.size()).isEqualTo(1);
	assertThat(studentEntities.get(0)).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(student);

	// clean up
	jdbc.update("DELETE FROM STUDENT", new MapSqlParameterSource());
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
  }

  @Test
  @DisplayName("should delete by id")
  void shouldDeleteById() {
	// given
	jdbc.update(
		"INSERT INTO STUDENT (ID, NAME, AGE, GRADES) VALUES (1000002, 'John', 22, '2,5,4,3,3');",
		new MapSqlParameterSource());
	long id = 1000002L;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	List<Student> studentEntities = fetchStudentEntities(1000002L);
	assertThat(studentEntities.size()).isEqualTo(0L);
  }

  @Test
  @DisplayName("should throw not found exception when delete student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteStudentIsNotExists() {
	// given
	long id = 1000001L;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("should update student")
  void shouldUpdateStudent() {
	// given
	jdbc.update(
		"INSERT INTO STUDENT (ID, NAME, AGE, GRADES) VALUES (1000003, 'John', 22, '2,5,4,3,3');",
		new MapSqlParameterSource());
	long id = 1000003L;
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

	List<Student> studentEntities = fetchStudentEntities(1000003L);
	assertThat(studentEntities.size()).isEqualTo(1);
	assertThat(studentEntities.get(0)).usingRecursiveComparison().isEqualTo(student);

	// clean up
	jdbc.update("DELETE FROM STUDENT", new MapSqlParameterSource());
  }

  @Test
  @DisplayName("should throw not found exception when update student is not exists")
  void shouldThrowNotFoundExceptionWhenDeleteUpdateIsNotExists() {
	// given
	long id = 3000000L;
	Student student = getStudent();

	// when
	Response response = putHttpCall(student, URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  private List<Student> fetchStudentEntities() {
	return jdbc.query("SELECT * FROM STUDENT WHERE ID < :id",
		new MapSqlParameterSource().addValue("id", 1000000),
		new BeanPropertyRowMapper<>(Student.class));
  }

  private List<Student> fetchStudentEntities(Long id) {
	return jdbc.query("SELECT * FROM STUDENT WHERE ID = :id",
		new MapSqlParameterSource().addValue("id", id),
		new BeanPropertyRowMapper<>(Student.class));
  }
}
