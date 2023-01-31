package com.example.demo.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  public static final String URL = "/api/student/";

  @Test
  @Disabled
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	long id = 100L;

	// when
	StudentEntity response = getHttpCall(URL + id).as(StudentEntity.class);

	//then
	assertThat(response).usingRecursiveComparison().isEqualTo(getStudent());
  }

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	StudentEntity response = postHttpCall(student, URL).as(StudentEntity.class);

	// then
	assertThat(response).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(
		student);
  }

  @Test
  @DisplayName("should throw exception when student is too old")
  void shouldThrowExceptionWhenStudentIsTooOld() {
	// given
	Student student = getStudent();
	student.setAge(122);

	// when
	Response response = postHttpCall(student, URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }

  @Test
  @DisplayName("should throw exception when student is too young")
  void shouldThrowExceptionWhenStudentIsTooYoung() {
	// given
	Student student = getStudent();
	student.setAge(12);

	// when
	Response response = postHttpCall(student, URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }
}
