package com.example.demo.commons.httpClientTest

import com.example.demo.commons.JsonMapper
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
abstract class AbstractMockMvcIntegrationSpec extends Specification implements JsonMapper {

    @Autowired
    MockMvc mockMvc

    <T> T getHttpCall(String url, int expectedStatusCode, Class<T> returnType) {
        return deserialize(mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is(expectedStatusCode))
                .andReturn().getResponse()
                .getContentAsString(), returnType)
    }

    <T> T postHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        return deserialize(mockMvc.perform(post(url)
                .content(serialize(body))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(expectedStatusCode))
                .andReturn().getResponse()
                .getContentAsString(), returnType)
    }

    <T> T putHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        return deserialize(mockMvc.perform(put(url)
                .content(serialize(body))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(expectedStatusCode))
                .andReturn().getResponse()
                .getContentAsString(), returnType)
    }

    void deleteHttpCall(String url, int expectedStatusCode) {
        mockMvc.perform(delete(url))
                .andDo(print())
                .andExpect(status().is(expectedStatusCode))
    }
}
