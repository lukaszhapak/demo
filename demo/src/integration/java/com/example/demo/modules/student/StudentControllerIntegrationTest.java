package com.example.demo.modules.student;

import static com.example.demo.commons.helper.TestUtils.getStudent;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.commons.JdbcTestHelper;
import com.example.demo.exception.ValidationExceptionDTO;
import com.example.demo.modules.student.domain.Student;
import io.restassured.response.Response;
import java.text.MessageFormat;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentControllerIntegrationTest extends AbstractIntegrationTest {

  private final String URL = "/api/student/";

  @Autowired
  private JdbcTestHelper<Student> jdbcTestHelper;

  private static final long NON_EXISTING_STUDENT_ID = 3000000L;
  private static final long SAMPLE_STUDENT_ID = 1000001L;


  @Test
  @DisplayName("should get student by id")
  void shouldGetStudentById() {
	// given
	Long id = SAMPLE_STUDENT_ID;

	// when
	Response response = getHttpCall(URL + id);
	Student studentResponse = response.as(Student.class);

	//then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id").isEqualTo(getStudent());
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
	assertThat(response.getBody().asString()).isEqualTo(MessageFormat.format("Student with id:{0} not found", id));
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

	Student studentEntity = jdbcTestHelper.fetchEntity(studentResponse.getId(), "STUDENT", Student.class);
	assertThat(studentEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
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
	ValidationExceptionDTO validationExceptionDTO = response.getBody().as(ValidationExceptionDTO.class);
	assertThat(validationExceptionDTO.getMessage()).isEqualTo("Student is invalid");
	assertThat(validationExceptionDTO.getInvalidFields()).hasSize(2).containsKeys("Name", "Age");

	List<Student> studentEntities = jdbcTestHelper.fetchEntities("STUDENT", Student.class);
	assertThat(studentEntities.size()).isEqualTo(0);
  }

  @Test
  @DisplayName("should delete by id")
  void shouldDeleteById() {
	// given
	Long id = SAMPLE_STUDENT_ID;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	assertThat(jdbcTestHelper.existsById(id, "STUDENT")).isFalse();
  }

  @Test
  @DisplayName("should throw not found exception when delete student not exists")
  void shouldThrowNotFoundExceptionWhenDeleteStudentNotExists() {
	// given
	long id = NON_EXISTING_STUDENT_ID;

	// when
	Response response = deleteHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
	assertThat(response.getBody().asString()).isEqualTo(MessageFormat.format("Student with id:{0} not found", id));
  }

  @Test
  @DisplayName("should update student")
  void shouldUpdateStudent() {
	// given
	long id = SAMPLE_STUDENT_ID;
	Student student = getStudent();
	student.setName("Jim");
	student.setAge(41);
	student.setGrades(List.of(5, 5, 5, 5, 5));

	// when
	Response response = putHttpCall(student, URL + id);
	Student studentResponse = response.as(Student.class);

	// then
	student.setId(id);
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	assertThat(studentResponse.getId()).isEqualTo(id);
	assertThat(studentResponse).usingRecursiveComparison().isEqualTo(student);

	Student studentEntity = jdbcTestHelper.fetchEntity(id, "STUDENT", Student.class);
	assertThat(studentEntity).usingRecursiveComparison().isEqualTo(student);
  }

  @Test
  @DisplayName("should throw not found exception when update student not exists")
  void shouldThrowNotFoundExceptionWhenUpdateStudentNotExists() {
	// given
	long id = NON_EXISTING_STUDENT_ID;
	Student student = getStudent();

	// when
	Response response = putHttpCall(student, URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
	assertThat(response.getBody().asString()).isEqualTo(MessageFormat.format("Student with id:{0} not found", id));
  }

  @Test
  @DisplayName("should get page")
  void shouldGetPage() {
	// given

	// when
	Response response = getHttpCall(URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
  }
}
