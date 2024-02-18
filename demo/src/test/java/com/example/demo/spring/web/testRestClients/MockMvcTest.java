package com.example.demo.spring.web.testRestClients;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractMockMvcIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MockMvcTest extends AbstractMockMvcIntegrationTest {

  private static final String URL = "/api/student/";

  private static Student getStudent() {
	return Student.builder().name("John").age(25).build();
  }

  @Test
  @DisplayName("should post get and delete student")
  void shouldPostGetAndDeleteStudent() {
	// given
	Long id = postHttpCall(URL, getStudent(), Student.class, 200).getId();

	// when
	Student response = getHttpCall(URL + id, Student.class, 200);
	deleteHttpCall(URL + id, 200);

	// then
	assertThat(response).usingRecursiveComparison()
		.ignoringFields("id")
		.isEqualTo(getStudent());
  }

  @Test
  @DisplayName("should post and get students")
  void shouldPostAndGetStudents() {
	// given
	postHttpCall(URL, getStudent(), Student.class, 200);
	postHttpCall(URL, getStudent(), Student.class, 200);

	// when
	List<Student> studentsResponse = List.of(getHttpCall(URL, Student[].class, 200));

	// then
	assertThat(studentsResponse.size()).isGreaterThan(1);
  }

  @Test
  @DisplayName("should post update get and delete student")
  void shouldPostUpdateGetAndDeleteStudent() {
	// given
	Student student = getStudent();
	Long id = postHttpCall(URL, student, Student.class, 200).getId();

	// when
	student.setName("Jim");
	putHttpCall(URL + id, student, Student.class, 200);
	Student response = getHttpCall(URL + id, Student.class, 200);
	deleteHttpCall(URL + id, 200);

	// then
	assertThat(response.getName()).isEqualTo(student.getName());
  }
}