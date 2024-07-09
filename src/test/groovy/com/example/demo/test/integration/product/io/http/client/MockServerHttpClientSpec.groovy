package com.example.demo.test.integration.product.io.http.client

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(properties = "product.externalService.url=http://localhost:8081")
class MockServerHttpClientSpec extends Specification implements SampleProducts {

    ClientAndServer mockServer

    @Autowired
    ProductService productService

    void setup() {
        mockServer = ClientAndServer.startClientAndServer(8081)
    }

    void cleanup() {
        mockServer.stop()
    }

    def "should get value from external service"() {
        given:
        mockExternalService(200, '{"value" : "value-from-mock-server"}')
        Long id = productService.save(sampleProduct).getId()

        when:
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id).getClientValue() == "value-from-mock-server"
    }

    void mockExternalService(int statusCode, String body) {
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/api/value"))
                .respond(
                        HttpResponse.response()
                                .withStatusCode(statusCode)
                                .withBody(body)
                                .withHeader("Content-Type", "application/json")
                )
    }
}
