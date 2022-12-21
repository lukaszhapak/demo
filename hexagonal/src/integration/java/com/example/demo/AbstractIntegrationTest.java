package com.example.demo;

import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  protected Response getHttpCall(String url) {
    return given()
        .port(port)
        .log().all()
        .when()
        .log().all()
        .get(url);
  }

  protected Response postHttpCall(Object body, String url) {
    return given()
        .port(port)
        .body(body)
        .contentType(JSON)
        .log().all()
        .when()
        .log().all()
        .post(url);
  }

}
