package com.example.demo.test.integration.product.io.data.testData.insert

import com.example.demo.common.httpClientTest.AbstractMockMvcIntegrationSpec
import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ManualInsertSpec extends AbstractMockMvcIntegrationSpec implements SampleProducts {

    @Autowired
    ProductService productService

    def "should get product inserted by method call"() {
        given:
        Long id = productService.save(sampleProduct).getId()

        expect:
        getHttpCall("/api/product/" + id, 200, Product.class) != null
    }

    def "should get product inserted by http"() {
        given:
        Long id = postHttpCall("/api/product", 200, sampleProduct, Product.class).getId()

        expect:
        getHttpCall("/api/product/" + id, 200, Product.class) != null
    }
}
