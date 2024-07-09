package com.example.demo.test.integration.product.dsl

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProductSpec extends Specification implements SampleProducts, DslSpec {

    @Autowired
    ProductService productService

    def "should save product"() {
        when:
        Long id = saveProduct(sampleProduct).getId()

        then:
        getProduct(id) != null
    }

    @Override
    Product saveProduct(Product product) {
        productService.save(product)
    }

    @Override
    Product getProduct(long id) {
        productService.getById(id)
    }
}
