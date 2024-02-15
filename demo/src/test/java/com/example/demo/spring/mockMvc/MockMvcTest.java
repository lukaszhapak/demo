package com.example.demo.spring.mockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.commons.JsonMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MockMvcTest implements JsonMapper {

  private static final String URL = "/mock-mvc/student/";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("should post get and delete student")
  void shouldPostGetAndDeleteStudent() throws Exception {
	// given
	Student request = Student.builder().name("John").age(25).build();
	Long id = postHttpCall(URL, request, Student.class, 200).getId();

	// when
	Student response = getHttpCall(URL + id, Student.class, 200);
	deleteHttpCall(URL + id, 200);

	// then
	assertThat(response).usingRecursiveComparison()
		.ignoringFields("id")
		.isEqualTo(request);
  }

  @Test
  @DisplayName("should post and get students")
  void shouldPostAndGetStudents() throws Exception {
	// given
	Student jim = Student.builder().name("Jim").age(22).build();
	Student john = Student.builder().name("John").age(25).build();
	postHttpCall(URL, john, Student.class, 200);
	postHttpCall(URL, jim, Student.class, 200);

	// when
	List<Student> studentsResponse = List.of(getHttpCall(URL, Student[].class, 200));

	// then
	assertThat(studentsResponse).usingRecursiveComparison()
		.ignoringFields("id")
		.ignoringCollectionOrder()
		.isEqualTo(List.of(jim, john));
  }

  private <T> T getHttpCall(String url, Class<T> returnType, int expectedStatusCode) throws Exception {
	return deserialize(mockMvc.perform(get(url))
		.andExpect(status().is(expectedStatusCode))
		.andReturn().getResponse()
		.getContentAsString(), returnType);
  }

  private <T> T postHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) throws Exception {
	return deserialize(mockMvc.perform(post(url)
			.content(serialize(body))
			.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(expectedStatusCode))
		.andReturn().getResponse()
		.getContentAsString(), returnType);
  }

  private void deleteHttpCall(String url, int expectedStatusCode) throws Exception {
	mockMvc.perform(delete(url))
		.andExpect(status().is(expectedStatusCode));
  }
}