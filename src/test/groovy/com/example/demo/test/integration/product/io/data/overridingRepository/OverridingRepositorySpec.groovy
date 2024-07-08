package com.example.demo.test.integration.product.io.data.overridingRepository

import com.example.demo.test.integration.product.SampleProducts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OverridingRepositorySpec extends Specification implements SampleProducts {

    @Autowired
    SpecProductRepository productRepository

    def "should get product by integer value"() {
        given:
        productRepository.save(sampleProduct.setIntegerValue(47))

        expect:
        productRepository.findByIntegerValue(47).isPresent()
        productRepository.count() >= 1
        productRepository.findAll() != null
    }
}
