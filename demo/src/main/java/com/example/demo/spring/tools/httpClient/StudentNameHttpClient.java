package com.example.demo.spring.tools.httpClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudentNameHttpClient {

  @Value("${nameService.url}")
  private String url;

  String getName(String source) {
	Response response = getHttpCall(url + "/api/name/" + source);
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
