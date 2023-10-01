package com.example.demo.spring.requestparam;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

class RequestParamControllerTest extends AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  @Test
  @DisplayName("should get with query params")
  void shouldGetWithQueryParams() {
	String url = "/params?name=John&surname=Doe&age=24";

	Response httpResponse = getHttpCall(url, port);

	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
  }
}