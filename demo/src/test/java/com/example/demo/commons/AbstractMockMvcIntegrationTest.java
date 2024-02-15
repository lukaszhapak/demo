package com.example.demo.commons;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractMockMvcIntegrationTest implements JsonMapper {

  @Autowired
  protected MockMvc mockMvc;

  protected <T> T getHttpCall(String url, Class<T> returnType, int expectedStatusCode) {
	try {
	  return deserialize(mockMvc.perform(get(url))
		  .andExpect(status().is(expectedStatusCode))
		  .andReturn().getResponse()
		  .getContentAsString(), returnType);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

  protected <T> T postHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) {
	try {
	  return deserialize(mockMvc.perform(post(url)
			  .content(serialize(body))
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
	  return deserialize(mockMvc.perform(put(url)
			  .content(serialize(body))
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
