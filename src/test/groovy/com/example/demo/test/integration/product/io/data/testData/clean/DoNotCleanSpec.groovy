package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DoNotCleanSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should save product"() {
        expect:
        // generate new values for unique columns
        // do not use assertions on count
        productService.save(sampleProduct.setName("TestName#0"))
    }
}
