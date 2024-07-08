package com.example.demo.test.integration.product.io.data.search

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ParameterizedInsertOneSearchSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    void setup() {
        productRepository.deleteAll()
        productService.save(firstProduct)
    }

    // cannot use instance of criteria in parameterized test it wont be recreated so using factory method instead

    def "should search products"() {
        expect:
        productService.getProducts(criteria).size() == size

        where:
        criteria                                   | size
        getSearchCriteria()                        | 1
        getSearchCriteria().setName("First")       | 1
        getSearchCriteria().setName("Second")      | 0
        getSearchCriteria().setMinIntegerValue(10) | 1
        getSearchCriteria().setMinIntegerValue(15) | 0
        getSearchCriteria().setMaxIntegerValue(15) | 1
        getSearchCriteria().setMaxIntegerValue(10) | 0
    }
}
