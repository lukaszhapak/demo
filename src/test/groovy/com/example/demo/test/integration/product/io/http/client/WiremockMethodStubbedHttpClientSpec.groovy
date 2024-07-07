package com.example.demo.test.integration.product.io.http.client


import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

@AutoConfigureWireMock(port = 0)
@SpringBootTest(properties = "product.externalService.url=http://localhost:\${wiremock.server.port}")
class WiremockMethodStubbedHttpClientSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should get value from external service"() {
        given:
        stubExternalService(200, '{"value" : "value-from-method"}')
        Long id = productService.save(sampleProduct).getId()

        when:
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id).getClientValue() == "value-from-method"
    }

    void stubExternalService(int status, String body) {
        stubFor(get(urlEqualTo("/api/value"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withBody(body)
                        .withHeader("Content-Type", "application/json")
                ))
    }
}
