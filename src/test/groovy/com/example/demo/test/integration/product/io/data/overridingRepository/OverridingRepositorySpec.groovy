package com.example.demo.test.integration.product.io.data.overridingRepository

import com.example.demo.test.integration.product.SampleProducts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OverridingRepositorySpec extends Specification implements SampleProducts {

    @Autowired
    SpecProductRepository productRepository

    def "should get product by quantity"() {
        given:
        productRepository.save(sampleProduct.setQuantity(47))

        expect:
        productRepository.findByQuantity(47).isPresent()
        productRepository.count() >= 1
        productRepository.findAll() != null
    }
}
