package com.example.demo.test.integration.product.dsl

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProductSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should save product"() {
        when:
        Long id = saveProduct(sampleProduct)

        then:
        getProduct(id) != null
    }

    long saveProduct(Product product) {
        productService.save(product).getId()
    }

    Product getProduct(long id) {
        productService.getById(id)
    }
}
