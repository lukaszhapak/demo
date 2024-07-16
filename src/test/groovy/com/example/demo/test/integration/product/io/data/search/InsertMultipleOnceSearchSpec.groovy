package com.example.demo.test.integration.product.io.data.search

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class InsertMultipleOnceSearchSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    ProductRepository productRepository

    // cannot use instance of criteria in parameterized test it wont be recreated so using factory method instead

    def "should get products"() {
        given:
        productService.save(firstProduct)
        productService.save(secondProduct)
        productService.save(thirdProduct)

        expect:
        productService.getProducts(getSearchCriteria()).size() > 0
        productService.getProducts(getSearchCriteria().setName("First")).stream().allMatch { it -> it.getName() == "First" }
        productService.getProducts(getSearchCriteria().setMaxIntegerValue(20)).stream().allMatch { it -> it.getIntegerValue() <= 20 }
        productService.getProducts(getSearchCriteria().setMinIntegerValue(15)).stream().allMatch { it -> it.getIntegerValue() >= 15 }

        cleanup:
        productRepository.deleteAll()
    }
}
