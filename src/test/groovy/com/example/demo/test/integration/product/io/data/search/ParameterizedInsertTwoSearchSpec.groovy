package com.example.demo.test.integration.product.io.data.search

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ParameterizedInsertTwoSearchSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    void setup() {
        productRepository.deleteAll()
        productService.save(firstProduct)
        productService.save(secondProduct)
    }

    // cannot use instance of criteria in parameterized test it wont be recreated so using factory method instead

    def "should search products"() {
        expect:
        productService.getProducts(criteria).get(0).getName() == name

        where:
        criteria                                   | name
        getSearchCriteria().setName("First")       | "First"
        getSearchCriteria().setMinIntegerValue(20) | "Second"
        getSearchCriteria().setMaxIntegerValue(15) | "First"
    }
}
