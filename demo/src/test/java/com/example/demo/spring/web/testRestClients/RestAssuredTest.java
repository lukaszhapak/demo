package com.example.demo.spring.web.testRestClients;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestAssuredTest extends AbstractRestAssuredIntegrationTest {

  @Test
  @DisplayName("should get with query params")
  void shouldGetWithQueryParams() {
	String url = "/api/all?name=Jim&surname=Doe&age=24&ids=2,5,4";
	Student student = new Student(1L, "John", 24);

	Response httpResponse = postHttpCall(url, port, getHeaders(), student);

	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
	ResponseDTO responseDTO = httpResponse.as(ResponseDTO.class);
	assertThat(responseDTO.getUserId()).isEqualTo("12344");
	assertThat(responseDTO.getStudent().getName()).isEqualTo("John");
	assertThat(responseDTO.getParamsDTO().getName()).isEqualTo("Jim");
  }

  Headers getHeaders() {
	return new Headers(List.of(new Header("user-id", "12344")));
  }

  @Test
  @DisplayName("single param")
  void singleParam() {
	// given
	String url = "/api/single/param?name=asd";

	// when
	Response httpResponse = getHttpCall(url, port);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
  }

  @Test
  @DisplayName("single param empty")
  void singleParamEmpty() {
	// given
	String url = "/api/single/param";

	// when
	Response httpResponse = getHttpCall(url, port);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_BAD_REQUEST);
  }
}