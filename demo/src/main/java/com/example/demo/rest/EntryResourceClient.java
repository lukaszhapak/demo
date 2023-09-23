package com.example.demo.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class EntryResourceClient {

  Entry processEntry(Entry entry) {
	Response response = postHttpCall(entry, "http://localhost:8081/batch-resource/api/entry/process", 8081);
	int statusCode = response.getStatusCode();
	if (statusCode == 500) {
//	  throw new SystemProcessingException();
	} else if (statusCode == 400) {
//	  throw new BusinessProcessingException();
	}
	return entry;
  }

  protected Response postHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.when()
		.post(url);
  }
}
