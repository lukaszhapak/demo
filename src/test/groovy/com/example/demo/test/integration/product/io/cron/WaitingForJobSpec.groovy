package com.example.demo.test.integration.product.io.cron

import com.example.demo.test.integration.product.TestData
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(properties = "product.cron=* * * ? * *")
class WaitingForJobSpec extends Specification {

    @Autowired
    ProductService productService

    def "should save product and run job"() {
        given:
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)
        Long id = productService.save(TestData.getSampleProduct()).getId()

        expect:
        pollingConditions.eventually {
            productService.getById(id).isCronValue()
        }
    }
}
