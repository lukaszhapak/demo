package com.example.demo.spring.http.server.exceptionHandler

import com.example.demo.common.AbstractMockMvcIntegrationSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ExceptionHandlerSpec extends AbstractMockMvcIntegrationSpec {

    def "should get not found"() {
        expect:
        mockMvc.perform(get("/api/404"))
                .andExpect(status().is(404))
    }

    def "should get system error"() {
        expect:
        mockMvc.perform(get("/api/500"))
                .andExpect(status().is(500))
    }
}

