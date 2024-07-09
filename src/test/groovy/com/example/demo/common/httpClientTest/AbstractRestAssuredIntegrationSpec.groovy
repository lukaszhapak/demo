package com.example.demo.common.httpClientTest


import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.http.Headers
import io.restassured.response.Response
import io.restassured.specification.RequestSender
import io.restassured.specification.RequestSpecification
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

    <T> T getHttpCall(String url, int expectedStatusCode, Class<T> returnType) {
        get(requestSpecification(), expectedStatusCode, url)
                .as(returnType)
    }

    Response getHttpCall(String url, int expectedStatusCode) {
        get(requestSpecification(), expectedStatusCode, url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Headers headers) {
        get(requestSpecification().headers(headers), expectedStatusCode, url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Map<String, Object> params) {
        get(requestSpecification().params(params), expectedStatusCode, url)
    }

    Response getHttpCall(String url, int expectedStatusCode, Headers headers, Map<String, Object> params, Object body) {
        get(requestSpecification().headers(headers)
                .params(params)
                .body(body)
                .contentType(ContentType.JSON), expectedStatusCode, url)
    }

    <T> T postHttpCall(String url, int expectedStatusCode, Object body, Class<T> returnType) {
        post(requestSpecification().body(body)
                .contentType(ContentType.JSON), expectedStatusCode, url)
                .as(returnType)
    }

    Response postHttpCall(String url, int expectedStatusCode, Object body) {
        post(requestSpecification().body(body)
                .contentType(ContentType.JSON), expectedStatusCode, url)
    }

    Response putHttpCall(String url, int expectedStatusCode, Object body) {
        put(requestSpecification().body(body)
                .contentType(ContentType.JSON), expectedStatusCode, url)
    }

    Response deleteHttpCall(String url, int expectedStatusCode) {
        delete(requestSpecification(), expectedStatusCode, url)
    }

    private Response get(RequestSpecification type, int expectedStatusCode, String url) {
        requestSender(type, expectedStatusCode).get(url)
    }

    private Response post(RequestSpecification type, int expectedStatusCode, String url) {
        requestSender(type, expectedStatusCode).post(url)
    }

    private Response put(RequestSpecification type, int expectedStatusCode, String url) {
        requestSender(type, expectedStatusCode).put(url)
    }

    private Response delete(RequestSpecification type, int expectedStatusCode, String url) {
        requestSender(type, expectedStatusCode).delete(url)
    }

    private RequestSpecification requestSpecification() {
        RestAssured.given()
                .port(port)
                .log().all()
    }

    private RequestSender requestSender(RequestSpecification type, int expectedStatusCode) {
        type.expect().statusCode(expectedStatusCode)
                .when()
    }
}
