package com.example.demo.api;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  protected Response getHttpCall(int serverPortNumber, Headers headers, String url) {
    return given()
        .port(serverPortNumber)
        .headers(headers)
        .log().all()
        .when()
        .log().all()
        .get(url);
  }

  protected Response postHttpCall(int serverPortNumber, Headers headers, Object body, String url) {
    return given()
        .port(serverPortNumber)
        .headers(headers)
        .body(body)
        .log().all()
        .when()
        .log().all()
        .post(url);
  }

}
