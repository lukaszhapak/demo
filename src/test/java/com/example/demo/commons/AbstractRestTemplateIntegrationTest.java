package com.example.demo.commons;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
	properties = {"logging.level.org.springframework.web.client.RestTemplate=DEBUG"})
public abstract class AbstractRestTemplateIntegrationTest {

  @Autowired
  protected TestRestTemplate restTemplate;

  protected <T> T postHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) {
	ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, body, returnType);
	assertThat(responseEntity.getStatusCode().value()).isEqualTo(expectedStatusCode);
	return responseEntity.getBody();
  }

  protected <T> T getHttpCall(String url, Class<T> returnType, int expectedStatusCode) {
	ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, returnType);
	assertThat(responseEntity.getStatusCode().value()).isEqualTo(expectedStatusCode);
	return responseEntity.getBody();
  }
}
