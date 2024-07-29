package com.example.demo.common.httpClientTest


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.assertj.core.api.AssertionsForClassTypes.assertThat
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = "logging.level.org.springframework.web.client.RestTemplate=DEBUG")
abstract class AbstractRestTemplateIntegrationSpec extends Specification {

    @Autowired
    protected TestRestTemplate restTemplate

    protected <T> T postHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, body, returnType)
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(expectedStatusCode)
        return responseEntity.getBody()
    }

    protected <T> T getHttpCall(String url, int expectedStatusCode, Class<T> returnType) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, returnType)
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(expectedStatusCode)
        return responseEntity.getBody()
    }
}
