package com.example.demo.test.integration.product.io.http.client

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import com.xebialabs.restito.server.StubServer
import org.glassfish.grizzly.http.util.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp
import static com.xebialabs.restito.semantics.Action.*
import static com.xebialabs.restito.semantics.Condition.get
import static org.glassfish.grizzly.http.util.HttpStatus.*

@SpringBootTest(properties = "product.externalService.url=http://localhost:8080")
class RestitoHttpClientSpec extends Specification implements SampleProducts {

    StubServer server

    @Autowired
    ProductService productService

    void setup() {
        server = new StubServer(8080).run()
    }

    void cleanup() {
        server.stop()
    }

    def "should get value from external service"() {
        given:
        mockExternalService(OK_200, '{"value" : "value-from-restito"}')
        Long id = productService.save(sampleProduct).getId()

        when:
        productService.assignValueFromExternalService(id)

        then:
        productService.getById(id).getClientValue() == "value-from-restito"
    }

    void mockExternalService(HttpStatus statusCode, String body) {
        whenHttp(server)
                .match(get("/api/value"))
                .then(status(statusCode), stringContent(body), header("Content-Type", "application/json"))
    }
}
