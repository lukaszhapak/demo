package com.example.demo.spring.http.server.handlerInterceptor

import com.example.demo.common.httpClientTest.AbstractMockMvcIntegrationSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class HandlerInterceptorSpec extends AbstractMockMvcIntegrationSpec {

    def "should get string and trigger handler interceptor"() {
        expect:
        mockMvc.perform(get("/api/string"))
                .andExpect(status().is(200))
    }
}

