package com.example.demo.test.integration.product.testContainer.jdbc


import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired

class FirstContainerSpec extends AbstractContainerSpec {

    @Autowired
    ProductService productService

    def "should save product"() {
        expect:
        productService.save(sampleProduct).getId() != null
    }
}
