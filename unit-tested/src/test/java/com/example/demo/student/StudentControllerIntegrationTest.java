package com.example.demo.student;

import com.example.demo.AbstractIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class StudentControllerIntegrationTest extends AbstractIntegrationTest implements
	StudentControllerTest {

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = 100L;

	// when
	Student student = given()
		.log().all()
		.port(port)
		.expect()
		.statusCode(200)
		.when()
		.get("/api/student/" + id)
		.getBody().as(Student.class);

	//then
	assertThat(studentsAreEqual(student, getStudent())).isTrue();
  }

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = getStudent();

	// when
	Student response = given()
		.log().all()
		.port(port)
		.body(student)
		.contentType(ContentType.JSON)
		.expect()
		.statusCode(200)
		.when()
		.post("/api/student/")
		.getBody().as(Student.class);

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
	String response = given()
		.log().all()
		.port(port)
		.body(student)
		.contentType(ContentType.JSON)
		.expect()
		.statusCode(400)
		.when()
		.post("/api/student/")
		.getBody().asString();

	// then
  }


  @Test
  @DisplayName("should throw exception when student is too young")
  void shouldThrowExceptionWhenStudentIsTooYoung() {
	// given
	Student student = getStudent();
	student.setAge(12);

	// when
	String response = given()
		.log().all()
		.port(port)
		.body(student)
		.contentType(ContentType.JSON)
		.expect()
		.statusCode(400)
		.when()
		.post("/api/student/")
		.getBody().asString();
  }
}
