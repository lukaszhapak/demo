// https://docs.spring.io/spring-boot/appendix/test-auto-configuration/slices.html

package com.example.demo.test.integration.product.slices

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class JpaSliceSpec extends Specification implements SampleProducts {

    @Autowired
    ProductRepository productRepository

    def "should save product"() {
        when:
        Long id = productRepository.save(sampleProduct).getId()

        then:
        productRepository.findById(id) != null
    }
}
