// https://docs.spring.io/spring-boot/appendix/test-auto-configuration/slices.html

package com.example.demo.test.integration.product.slices

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@AutoConfigureMockMvc
class WebSliceSpec extends Specification implements SampleProducts {

    @MockBean
    ProductService productService

    @Autowired
    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())

    def "should save product"() {
        given:
        productService.save(_ as Product) >> sampleProduct

        expect:
        postHttpCall("/api/product", 200, sampleProduct)
    }

    void postHttpCall(String url, int expectedStatusCode, Object body) {
        mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode))

    }
}
