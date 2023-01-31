package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  public static final String URL = "/api/student/";

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	jdbc.execute(
		"INSERT INTO STUDENT (ID, NAME, AGE) VALUES (1000000, 'John', 22);");
	long id = 1000000L;

	// when
	StudentEntity response = getHttpCall(URL + id).as(StudentEntity.class);

	//then
	assertThat(response.getId()).isEqualTo(id);
	assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(getStudent());

	// clean up
	jdbc.execute("DELETE FROM STUDENT");
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

	// clean up
	jdbc.execute("DELETE FROM STUDENT");
  }
}
