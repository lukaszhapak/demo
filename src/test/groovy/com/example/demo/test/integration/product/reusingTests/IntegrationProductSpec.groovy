package com.example.demo.test.integration.product.reusingTests

import com.example.demo.test.integration.product.data.Product
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationProductSpec extends AbstractProductSpec {

    @Override
    Product saveProduct(Product product) {
        /// using http or message queue or whatever

        postHttpCall("/api/product", 200, sampleProduct, Product.class)
    }

    @Override
    Product getProduct(long id) {
        getHttpCall("/api/product/" + id, 200, Product.class)
    }





































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

}
