package com.example.demo.spring.http.client.restAssured

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

import static com.github.tomakehurst.wiremock.client.WireMock.*

@AutoConfigureWireMock(port = 0)
@ActiveProfiles("wiremock")
class RestAssuredHttpClientSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService
    @Autowired
    StudentRepository studentRepository

    def "should save student with method stubbed endpoint"() {
        given:
        stubNameService(200, '{"name" : "name-from-method"}')
        Student student = new Student()

        when:
        Long id = studentService.save(student).getId()

        then:
        studentRepository.findById(id).get().getName() == "name-from-method"
    }

    void stubNameService(int status, String body) {
        stubFor(get(urlEqualTo("/api/name"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withBody(body)
                        .withHeader("Content-Type", "application/json")
                ))
    }
}
