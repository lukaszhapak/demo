package com.example.demo.common

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.http.Headers
import io.restassured.response.Response
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class AbstractRestAssuredIntegrationSpec extends Specification {

    @LocalServerPort
    int port

    Response getHttpCall(String url, int expectedStatusCode) {
        RestAssured.given()
                .port(port)
                .log().all()
                .expect().statusCode(expectedStatusCode)
                .when()
                .get(url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Headers headers) {
        RestAssured.given()
                .port(port)
                .headers(headers)
                .log().all()
                .expect().statusCode(expectedStatusCode)
                .when()
                .get(url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Map<String, Object> params) {
        RestAssured.given()
                .port(port)
                .params(params)
                .log().all()
                .expect().statusCode(expectedStatusCode)
                .when()
                .get(url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Headers headers, Map<String, Object> params, Object body) {
        RestAssured.given()
                .port(port)
                .headers(headers)
                .params(params)
                .body(body)
                .contentType(ContentType.JSON)
                .log().all()
                .expect().statusCode(expectedStatusCode)
                .when()
                .get(url)
    }
}
