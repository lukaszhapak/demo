package com.example.demo.test.integration.product.io.cron

import com.example.demo.test.integration.product.TestData
import com.example.demo.test.integration.product.cron.ProductJob
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(properties = "product.cron=-")
class ManuallyExecutingJobSpec extends Specification {

    @Autowired
    ProductJob productJob

    @Autowired
    ProductService productService

    def "should save product and run job"() {
        given:
        Long id = productService.save(TestData.getSampleProduct()).getId()

        when:
        productJob.setCronValue()

        then:
        productService.getById(id).isCronValue()
    }
}
