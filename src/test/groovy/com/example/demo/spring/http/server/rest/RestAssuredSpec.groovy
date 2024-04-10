package com.example.demo.spring.http.server.rest

import com.example.demo.common.AbstractRestAssuredIntegrationSpec
import io.restassured.http.Header
import io.restassured.http.Headers

class RestAssuredSpec extends AbstractRestAssuredIntegrationSpec {

    def "should get string"() {
        expect:
        getHttpCall("/api/string", 200).asString() == "Student"
    }

    def "should get with single param in url"() {
        expect:
        getHttpCall("/api/param/single?singleParam=42112", 200).as(ResponseDTO).getSingleParam() == "42112"
    }

    def "should get with multiple params in url"() {
        given:
        String url = "/api/param/multi?name=John&age=24&ids=2,5,4"

        when:
        ParamsDTO paramsDTO = getHttpCall(url, 200).as(ResponseDTO).getParams()

        then:
        paramsDTO.getName() == "John"
        paramsDTO.getAge() == 24
        paramsDTO.getIds() == [2, 5, 4]
    }

    def "should get with multiple params as arguments"() {
        given:
        Map<String, Object> params = [
                "name": "Michael",
                "age" : 19,
                "ids" : [12, 2, 6]]
        String url = "/api/param/multi"

        when:
        ParamsDTO paramsDTO = getHttpCall(url, 200, params).as(ResponseDTO).getParams()

        then:
        paramsDTO.getName() == "Michael"
        paramsDTO.getAge() == 19
        paramsDTO.getIds() == [12, 2, 6]
    }

    def "should get with multiple headers"() {
        given:
        Headers headers = new Headers([
                new Header("name", "Jim"),
                new Header("age", "21"),
                new Header("ids", "3,1,2")])

        when:
        HeadersDTO headersDTO = getHttpCall("/api/header/multi", 200, headers).as(ResponseDTO).getHeaders()

        then:
        headersDTO.getName() == "Jim"
        headersDTO.getAge() == 21
        headersDTO.getIds() == [3, 1, 2]
    }

    def "should get with headers params body and path variable"() {
        given:
        Map<String, Object> params = [
                "name": "Michael",
                "age" : 31,
                "ids" : [3, 2, 9]]
        Headers headers = new Headers([new Header("singleHeader", "test-header")])
        Student student = new Student(2, "Jim", 42)

        when:
        ResponseDTO responseDTO = getHttpCall("/api/all/321", 200, headers, params, student).as(ResponseDTO)

        then:
        responseDTO.getPathVariable() == "321"
    }
}

