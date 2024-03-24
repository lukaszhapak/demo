package com.example.demo.spring.tools.httpClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureWireMock(port = 0)
@ActiveProfiles("wiremock")
class HttpClientTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;
  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student with method stubbed endpoint")
  void shouldSaveStudentWithMethodStubbedEndpoint() {
	// given
	stubNameService(200, "{\"name\" : \"method\"}");
	Student student = new Student().setSource("method");

	// when
	Long id = studentService.save(student).getId();

	// then
	assertThat(studentRepository.findById(id).get().getName()).isEqualTo("method");
  }

  @Test
  @DisplayName("should save student with resources stubbed endpoint")
  void shouldSaveStudentWithResourcesStubbedEndpoint() {
	// given
	// stubbed endpoint gets loaded from resources/mappings
	Student student = new Student().setSource("resources");

	// when
	Long id = studentService.save(student).getId();

	// then
	assertThat(studentRepository.findById(id).get().getName()).isEqualTo("resources");
  }

  static void stubNameService(int status, String body) {
	stubFor(
		WireMock
			.get(urlEqualTo("/api/name/method"))
			.willReturn(aResponse()
				.withStatus(status)
				.withBody(body)
				.withHeader("Content-Type", "application/json")
			));
  }
}