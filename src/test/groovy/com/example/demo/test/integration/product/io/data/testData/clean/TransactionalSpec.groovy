package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@SpringBootTest
class TransactionalSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should save first product"() {
        expect:
        productService.getByName("TestName#2") == null
        productService.save(sampleProduct.setName("TestName#2"))
    }

    def "should save second product"() {
        expect:
        productService.getByName("TestName#2") == null
        productService.save(sampleProduct.setName("TestName#2"))
    }

    def "should save third product"() {
        expect:
        productService.getByName("TestName#2") == null
        productService.save(sampleProduct.setName("TestName#2"))
    }
}
