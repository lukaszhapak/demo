package com.example.demo.spring.tools.restClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudentNameRestClient {

  @Value("${rest.url}")
  private String url;

  String getName() {
	Response response = getHttpCall(url + "/api/name/");
	int statusCode = response.getStatusCode();
	if (statusCode >= 500 && statusCode < 600) {
	  throw new RuntimeException();
	} else if (statusCode >= 400 && statusCode < 500) {
	  throw new RuntimeException();
	}
	return response.as(NameResponse.class).getName();
  }

  private Response getHttpCall(String url) {
	return RestAssured.given()
		.log().all()
		.when()
		.get(url);
  }
}
