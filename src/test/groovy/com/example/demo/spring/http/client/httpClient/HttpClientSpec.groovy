package com.example.demo.spring.http.client.httpClient

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

import static com.github.tomakehurst.wiremock.client.WireMock.*

@AutoConfigureWireMock(port = 0)
@ActiveProfiles("wiremock")
class HttpClientSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService
    @Autowired
    StudentRepository studentRepository

    def "should save student with method stubbed endpoint"() {
        given:
        stubExternalService(200, '{"value" : "test-rest-assured-value"}', "/api/rest-assured")
        stubExternalService(200, '{"value" : "test-rest-template-value"}', "/api/rest-template")
        Student student = new Student()

        when:
        Long id = studentService.save(student).getId()

        then:
        def savedStudent = studentRepository.findById(id).get()
        savedStudent.getValueFromRestAssured() == "test-rest-assured-value"
        savedStudent.getValueFromRestTemplate() == "test-rest-template-value"
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
