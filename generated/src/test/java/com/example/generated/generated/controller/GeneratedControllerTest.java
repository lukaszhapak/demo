package com.example.generated.generated.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.generated.generated.entity.Generated;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class GeneratedControllerTest {

  private final String URL = "/api/generated/";

  @LocalServerPort
  protected int port;

  @Autowired
  protected NamedParameterJdbcOperations jdbc;

  @Test
  @DisplayName("test all")
  void testAll() {
	Generated generated = getGenerated();
	generated.setId(1L);

	postHttpCall(generated, URL);

	Generated generatedResponse = getHttpCall(URL + 1).as(Generated.class);

	assertThat(generated).usingRecursiveComparison().isEqualTo(generatedResponse);

	generated.setName("Jimmy");

	putHttpCall(generated, URL);

	generatedResponse = getHttpCall(URL + 1).as(Generated.class);
	assertThat(generated).usingRecursiveComparison().isEqualTo(generatedResponse);

	deleteHttpCall(URL + 1);

	Response deleteResponse =  getHttpCall(URL + 1);
	assertThat(deleteResponse.asString()).isEqualTo("");
  }

  protected Response getHttpCall(String url) {
	return given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  protected Response postHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected Response deleteHttpCall(String url) {
	return given()
		.port(port)
		.contentType(JSON)
		.log().all()
		.when()
		.delete(url);
  }

  protected Response putHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.put(url);
  }

  private Generated getGenerated() {
	Generated generated = new Generated();
	generated.setName("John");
	generated.setAge(28);
	return generated;
  }
}