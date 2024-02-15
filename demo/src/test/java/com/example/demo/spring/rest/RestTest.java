package com.example.demo.spring.rest;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
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
	String url = "/post?name=Jim&surname=Doe&age=24&ids=2,5,4";
	Student student = new Student("John", 24);

	Response httpResponse = postHttpCall(url, port, getHeaders(), student);

	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
	ResponseDTO responseDTO = httpResponse.as(ResponseDTO.class);
	assertThat(responseDTO.getPlayerId()).isEqualTo("12344");
	assertThat(responseDTO.getStudent().getName()).isEqualTo("John");
	assertThat(responseDTO.getParams().getName()).isEqualTo("Jim");
  }

  private Headers getHeaders() {
	return new Headers(List.of(new Header("player-id", "12344")));
  }
}