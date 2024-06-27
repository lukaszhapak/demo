package com.example.demo.commons;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestAssuredIntegrationTest {

  @LocalServerPort
  protected int port;

  protected Response getHttpCall(String url, int port) {
	return RestAssured.given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  protected Response getHttpCall(String url) {
	return RestAssured.given()
		.log().all()
		.when()
		.get(url);
  }

  protected <T> T getHttpCall(String url, Class<T> returnType, int expectedStatusCode) {
	return RestAssured.given()
		.port(port)
		.log().all()
		.expect()
		.log().all()
		.statusCode(expectedStatusCode)
		.when()
		.get(url)
		.as(returnType);
  }

  protected Response postHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected Response postHttpCall(String url, int port, Headers headers, Object body) {
	return RestAssured.given()
		.port(port)
		.headers(headers)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected <T> T postHttpCall(String url, Object body, Class<T> returnType, int expectedStatusCode) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.log().all()
		.contentType(ContentType.JSON)
		.expect()
		.log().all()
		.statusCode(expectedStatusCode)
		.when()
		.post(url)
		.as(returnType);
  }

  protected Response deleteHttpCall(String url, int port) {
	return RestAssured.given()
		.port(port)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.delete(url);
  }

  protected Response putHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.put(url);
  }
}
