package com.example.demo.test.integration.product.io.http.client

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import spock.lang.Specification

@AutoConfigureWireMock(port = 0)
@SpringBootTest(properties = "product.externalService.url=http://localhost:\${wiremock.server.port}")
class WiremockResourceStubbedHttpClientSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should get value from external service"() {
        given:
        Long id = productService.save(sampleProduct).getId()

        when:
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id).getClientValue() == "value-from-resources"
    }
}
