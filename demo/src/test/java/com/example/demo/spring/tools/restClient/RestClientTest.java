package com.example.demo.spring.tools.restClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RestClientTest extends AbstractIntegrationTest {

  @Rule
  public WireMockRule server = new WireMockRule(10101);
  @Autowired
  StudentService studentService;
  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	server.start();
	server.stubFor(
		WireMock
			.get(urlEqualTo("/api/name/"))
			.willReturn(aResponse()
				.withStatus(200)
				.withBody("{\"name\" : \"John\"}")
				.withHeader("Content-Type", "application/json"))
	);
	Student student = new Student();

	// when
	studentService.save(student);

	// then
	assertThat(studentRepository.count()).isEqualTo(1);
  }
}