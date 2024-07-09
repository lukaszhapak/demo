package com.example.demo.common.httpClientTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class AbstractMockMvcIntegrationSpec extends Specification {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())

    @Autowired
    MockMvc mockMvc

    <T> T getHttpCall(String url, int expectedStatusCode, Class<T> returnType) {
        try {
            return objectMapper.readValue(mockMvc.perform(get(url))
                    .andDo(print())
                    .andExpect(status().is(expectedStatusCode))
                    .andReturn().getResponse()
                    .getContentAsString(), returnType)
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    <T> T postHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        try {
            return objectMapper.readValue(mockMvc.perform(post(url)
                    .content(objectMapper.writeValueAsString(body))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(expectedStatusCode))
                    .andReturn().getResponse()
                    .getContentAsString(), returnType)
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    <T> T putHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        try {
            return objectMapper.readValue(mockMvc.perform(put(url)
                    .content(objectMapper.writeValueAsString(body))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(expectedStatusCode))
                    .andReturn().getResponse()
                    .getContentAsString(), returnType)
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    void deleteHttpCall(String url, int expectedStatusCode) {
        try {
            mockMvc.perform(delete(url))
                    .andDo(print())
                    .andExpect(status().is(expectedStatusCode))
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }
}
