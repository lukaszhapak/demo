package com.example.demo.spring.modules.http.client.httpClient

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

import static com.github.tomakehurst.wiremock.client.WireMock.*

@AutoConfigureWireMock(port = 0)
@ActiveProfiles("wiremock")
class HttpClientSpec extends AbstractIntegrationSpec {

    @Autowired
    HttpClientService httpClientService

    def "should save student with method stubbed endpoint"() {
        given:
        stubExternalService(200, '{"value" : "test-rest-assured-value"}', "/api/rest-assured")
        stubExternalService(200, '{"value" : "test-rest-template-value"}', "/api/rest-template")
        stubExternalService(200, '{"value" : "test-feign-value"}', "/api/feign")

        when:
        HttpResponseDTO response = httpClientService.getResponse()

        then:
        response.getValueFromRestAssured() == "test-rest-assured-value"
        response.getValueFromRestTemplate() == "test-rest-template-value"
        response.getValueFromFeign() == "test-feign-value"
    }

    void stubExternalService(int status, String body, String url) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withBody(body)
                        .withHeader("Content-Type", "application/json")
                ))
    }
}
