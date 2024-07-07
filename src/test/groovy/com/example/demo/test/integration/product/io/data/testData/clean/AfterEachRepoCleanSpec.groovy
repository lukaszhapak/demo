package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AfterEachRepoCleanSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    void cleanup() {
        productRepository.deleteAll()
    }

    def "should save first product"() {
        expect:
        productService.getByName("TestName#4") == null
        productService.save(sampleProduct.setName("TestName#4"))
    }

    def "should save second product"() {
        expect:
        productService.getByName("TestName#4") == null
        productService.save(sampleProduct.setName("TestName#4"))
    }

    def "should save third product"() {
        expect:
        productService.getByName("TestName#4") == null
        productService.save(sampleProduct.setName("TestName#4"))
    }
}
