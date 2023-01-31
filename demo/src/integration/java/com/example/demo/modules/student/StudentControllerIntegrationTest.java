package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.commons.HelperClass;
import com.example.demo.exception.ValidationException;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  public static final String URL = "/api/student/";

  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	jdbc.execute(
		"INSERT INTO STUDENT (ID, NAME, AGE, GRADES) VALUES (1000000, 'John', 22, '2,5,4,3,3');");
	long id = 1000000L;

	// when
	Response response = getHttpCall(URL + id);
	Student studentResponse = response.as(Student.class);

	//then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id")
		.isEqualTo(getStudent());

	// clean up
	jdbc.execute("DELETE FROM STUDENT");
  }

  @Test
  @DisplayName("should get not found status when student do not exists")
  void shouldGetNotFoundStatusWhenStudentDoNotExists() {
	// given
	long id = 1000000L;

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
	assertThat(studentResponse).usingRecursiveComparison().isEqualTo(student);
	List<StudentEntity> studentEntities = fetchStudentEntities();
	assertThat(studentEntities.size()).isEqualTo(1);
	assertThat(studentEntities.get(0)).usingRecursiveComparison().ignoringFields("grades")
		.isEqualTo(student);
	assertThat(studentEntities.get(0).getGrades()).isEqualTo(
		HelperClass.collectionAsString(student.getGrades()));

	// clean up
	jdbc.execute("DELETE FROM STUDENT");
  }

  protected List<StudentEntity> fetchStudentEntities() {
	return jdbc.query("SELECT * FROM STUDENT",
		new BeanPropertyRowMapper<>(StudentEntity.class));
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

}
