package com.example.demo.test.integration.product.io.data.search

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class InsertMultipleSearchSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    void setup() {

        // can be also inserted via sql script or flyway migrations

        productRepository.deleteAll()
        productService.save(firstProduct)
        productService.save(secondProduct)
        productService.save(thirdProduct)
    }

    def "should get no criteria"() {
        expect:
        productService.getProducts(searchCriteria).size() > 0
    }

    def "should get by name"() {
        expect:
        productService.getProducts(searchCriteria.setName("First")).stream().allMatch { it -> it.getName() == "First" }
    }

    def "should get by min integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMinIntegerValue(20)).stream().allMatch { it -> it.getIntegerValue() >= 20 }
    }

    def "should get by max integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMaxIntegerValue(15)).stream().allMatch { it -> it.getIntegerValue() <= 15 }
    }
}
