package com.example.demo.spring.rest;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

class RestTest extends AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  @Test
  @DisplayName("should get with query params")
  void shouldGetWithQueryParams() {
	String url = "/post?name=John&surname=Doe&age=24&ids=2,5,4";
	Student student = new Student("John", 24);

	Response httpResponse = postHttpCall(url, port, getHeaders(), student);

	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
  }

  private Response postHttpCall(String url, int port, Headers headers, Object body) {
	return RestAssured.given()
		.port(port)
		.headers(headers)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }

  private Headers getHeaders() {
	return new Headers(List.of(new Header("player-id", "12344")));
  }
}