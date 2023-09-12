package com.example.demo.rest;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class RestClient {

  public void getPosts() {
	log.debug("getting posts");
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
