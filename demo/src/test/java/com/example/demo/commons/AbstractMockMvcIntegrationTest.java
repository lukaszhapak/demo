package com.example.demo.commons;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractMockMvcIntegrationTest {

  private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @Autowired
  protected MockMvc mockMvc;

  protected <T> T getHttpCall(String url, Class<T> returnType, int expectedStatusCode) {
	try {
	  return objectMapper.readValue(mockMvc.perform(get(url))
		  .andExpect(status().is(expectedStatusCode))
		  .andReturn().getResponse()
		  .getContentAsString(), returnType);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

  protected <T> T postHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) {
	try {
	  return objectMapper.readValue(mockMvc.perform(post(url)
			  .content(objectMapper.writeValueAsString(body))
			  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(status().is(expectedStatusCode))
		  .andReturn().getResponse()
		  .getContentAsString(), returnType);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

  protected <T> T putHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) {
	try {
	  return objectMapper.readValue(mockMvc.perform(put(url)
			  .content(objectMapper.writeValueAsString(body))
			  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(status().is(expectedStatusCode))
		  .andReturn().getResponse()
		  .getContentAsString(), returnType);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

  protected void deleteHttpCall(String url, int expectedStatusCode) {
	try {
	  mockMvc.perform(delete(url))
		  .andExpect(status().is(expectedStatusCode));
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }
}
