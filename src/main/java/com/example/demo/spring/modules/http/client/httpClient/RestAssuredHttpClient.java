package com.example.demo.spring.modules.http.client.httpClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class RestAssuredHttpClient {

  @Value("${external.service.url}")
  private final String url;

  String getValue() {
	Response response = getHttpCall(url + "/api/rest-assured");
	int statusCode = response.getStatusCode();
	if (statusCode >= 500 && statusCode < 600) {
	  throw new RuntimeException();
	} else if (statusCode >= 400 && statusCode < 500) {
	  throw new RuntimeException();
	}
	return response.as(ValueResponse.class).getValue();
  }

  private Response getHttpCall(String url) {
	return RestAssured.given()
		.log().all()
		.when()
		.get(url);
  }
}
