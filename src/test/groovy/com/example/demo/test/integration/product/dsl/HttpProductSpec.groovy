package com.example.demo.test.integration.product.dsl

import com.example.demo.common.httpClientTest.AbstractMockMvcIntegrationSpec
import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class HttpProductSpec extends AbstractMockMvcIntegrationSpec implements SampleProducts {

    def "should save product"() {
        when:
        Long id = saveProduct(sampleProduct)

        then:
        getProduct(id) != null
    }

    long saveProduct(Product product) {
        postHttpCall("/api/product", 200, product, Product.class).getId()
    }

    Product getProduct(long id) {
        getHttpCall("/api/product/" + id, 200, Product.class)
    }
}
