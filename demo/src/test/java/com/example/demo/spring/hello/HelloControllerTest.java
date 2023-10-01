package com.example.demo.spring.hello;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

class HelloControllerTest extends AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  @Test
  @DisplayName("should get hello")
  void shouldGetHello() {
	// when
	Response httpResponse = getHttpCall("/hello", port);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
  }
}
