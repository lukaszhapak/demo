package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcOperations
import spock.lang.Specification

@SpringBootTest
class JdbcTemplateSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    JdbcOperations jdbcOperations

    void setup() {
        jdbcOperations.execute("delete from product;")
    }

    def "should save first product"() {
        expect:
        productService.getByName("TestName#5") == null
        productService.save(sampleProduct.setName("TestName#5"))
    }

    def "should save second product"() {
        expect:
        productService.getByName("TestName#5") == null
        productService.save(sampleProduct.setName("TestName#5"))
    }

    def "should save third product"() {
        expect:
        productService.getByName("TestName#5") == null
        productService.save(sampleProduct.setName("TestName#5"))
    }
}
