package com.example.demo.spring.mockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MockMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("should post and get students")
  void shouldPostAndGetStudents() throws Exception {
	// given
	mockMvc.perform(
		post("/mock-mvc/student")
			.content("{ \"name\": \"John\", \"age\": 25 }")
			.contentType(MediaType.APPLICATION_JSON)
	).andExpect(status().isOk());

	mockMvc.perform(
		post("/mock-mvc/student")
			.content("{ \"name\": \"Jim\", \"age\": 22 }")
			.contentType(MediaType.APPLICATION_JSON)
	).andExpect(status().isOk());

	// when
	String jsonResponse = mockMvc.perform(get("/mock-mvc/student"))
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();

	// then
	assertThat(jsonResponse)
		.contains("{\"id\":1,\"name\":\"John\",\"age\":25}")
		.contains("{\"id\":2,\"name\":\"Jim\",\"age\":22}");
  }
}