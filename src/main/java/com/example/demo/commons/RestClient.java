package com.example.demo.commons;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public interface RestClient {

  default Response getHttpCall(String url, int port) {
	return RestAssured.given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  default Response postHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }

  default Response deleteHttpCall(String url, int port) {
	return RestAssured.given()
		.port(port)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.delete(url);
  }

  default Response putHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.put(url);
  }
}
