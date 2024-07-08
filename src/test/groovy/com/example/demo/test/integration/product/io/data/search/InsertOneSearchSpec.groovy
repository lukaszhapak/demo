package com.example.demo.test.integration.product.io.data.search

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class InsertOneSearchSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    void setup() {
        productRepository.deleteAll()
        productService.save(firstProduct)
    }

    def "should get no criteria"() {
        expect:
        productService.getProducts(searchCriteria).size() == 1
    }

    def "should get by name"() {
        expect:
        productService.getProducts(searchCriteria.setName("First")).size() == 1
    }

    def "should not get by name"() {
        expect:
        productService.getProducts(searchCriteria.setName("Second")).size() == 0
    }

    def "should get by min integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMinIntegerValue(10)).size() == 1
    }

    def "should not get by min integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMinIntegerValue(15)).size() == 0
    }

    def "should get by max integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMaxIntegerValue(15)).size() == 1
    }

    def "should not get by max integer value"() {
        expect:
        productService.getProducts(searchCriteria.setMaxIntegerValue(10)).size() == 0
    }
}
