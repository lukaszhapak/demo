package com.example.demo.test.integration.product.httpclient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductHttpClient {

  @Value("${product.externalService.url}")
  private final String url;

  String getValue() {
	Response response = getHttpCall(url + "/api/value/");
	return response.as(ValueResponse.class).getValue();
  }

  private Response getHttpCall(String url) {
	return RestAssured.given()
		.log().all()
		.when()
		.get(url);
  }
}
