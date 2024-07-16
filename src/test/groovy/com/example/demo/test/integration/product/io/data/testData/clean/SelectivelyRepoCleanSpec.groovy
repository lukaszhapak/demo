package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SelectivelyRepoCleanSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository


    def "should save first product"() {
        expect:
        productService.getByName("TestName#6") == null
        productService.save(sampleProduct.setName("TestName#6"))

        cleanup:
        productRepository.deleteAll()
        // or delete by id or some other field
    }

    def "should not find product"() {
        expect:
        productService.getByName("TestName#6") == null
    }

    def "should save second product"() {
        expect:
        productService.getByName("TestName#6") == null
        productService.save(sampleProduct.setName("TestName#6"))

        cleanup:
        productRepository.deleteAll()
    }
}
