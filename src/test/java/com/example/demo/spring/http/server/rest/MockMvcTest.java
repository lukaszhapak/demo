package com.example.demo.spring.http.server.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.spring.http.AbstractMockMvcIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MockMvcTest extends AbstractMockMvcIntegrationTest {

  static final String URL = "/api/student/";

  @Test
  @DisplayName("should send request with single param")
  void shouldSendRequestWithSingleParam() throws Exception {
	mockMvc.perform(get("/api/param/single")
			.param("name", "John"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("John")));
  }

  @Test
  @DisplayName("should send request with single param in url")
  void shouldSendRequestWithParamSingleInUrl() throws Exception {
	mockMvc.perform(get("/api/param/single?name=John"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("John")));
  }

  @Test
  @DisplayName("should send request with single header")
  void shouldSendRequestWithSingleHeader() throws Exception {
	mockMvc.perform(get("/api/header/single")
			.header("name", "John"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("John")));
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
  @DisplayName("should post update get student")
  void shouldPostUpdateGetStudent() {
	// given
	Student student = getStudent();
	Long id = postHttpCall(URL, student, Student.class, 200).getId();

	// when
	student.setName("Jim");
	putHttpCall(URL + id, student, Student.class, 200);
	Student response = getHttpCall(URL + id, Student.class, 200);

	// then
	assertThat(response.getName()).isEqualTo(student.getName());
  }

  static Student getStudent() {
	return Student.builder().name("John").age(25).build();
  }
}