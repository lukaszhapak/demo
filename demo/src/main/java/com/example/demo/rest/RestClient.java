package com.example.demo.rest;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.List;

class RestClient {

  public static void main(String[] args) {
	Response response = getHttpCall("https://jsonplaceholder.typicode.com/posts/");
	List<Post> posts = List.of(response.getBody().as(Post[].class));
  }

  protected static Response getHttpCall(String url) {
	return given()
		.log().all()
		.when()
		.get(url);
  }
}
