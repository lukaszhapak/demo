package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.common.httpClientTest.AbstractMockMvcIntegrationSpec
import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class TransactionalMockMvcSpec extends AbstractMockMvcIntegrationSpec implements SampleProducts {

    @Autowired
    ProductService productService

    void cleanup() {
        println("product by service1=" + productService.getByName("product by service#1"))
        println("product by http1=" + productService.getByName("product by mockmvc http#1"))
        println("product by service2=" + productService.getByName("product by service#2"))
        println("product by http2=" + productService.getByName("product by mockmvc http#2"))
    }

    def "should save first product via service"() {
        expect:
        productService.save(sampleProduct.setName("product by service#1"))
    }

    def "should save first product via http"() {
        expect:
        postHttpCall("/api/product", 200, sampleProduct.setName("product by mockmvc http#1"), Product.class)
    }

    def "should save second product via service"() {
        expect:
        productService.save(sampleProduct.setName("product by service#2"))
    }

    def "should save second product via http"() {
        expect:
        postHttpCall("/api/product", 200, sampleProduct.setName("product by mockmvc http#2"), Product.class)
    }
}
