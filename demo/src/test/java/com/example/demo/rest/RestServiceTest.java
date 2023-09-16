package com.example.demo.rest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Test;

class RestServiceTest {

  @Test
  void shouldGetData() {
	// given

	// when
//	String response = getHttpCall("http://127.0.0.1:5000/test").body().as(String.class);

	// then
  }

  @Test
  void shouldGetPost() {
	// given
	long id = 1;

	// when
	Response response = getHttpCall("https://jsonplaceholder.typicode.com/posts/" + id);
	Post post = response.getBody().as(Post.class);

	// then
	assertThat(response.statusCode()).isEqualTo(SC_OK);
	assertThat(post.getId()).isEqualTo(id);
	assertThat(post.getUserId()).isNotNull();
  }

  @Test
  void shouldGetPosts() {
	// when
	Response response = getHttpCall("https://jsonplaceholder.typicode.com/posts/");
	List<Post> posts = List.of(response.getBody().as(Post[].class));

	// then
	assertThat(response.statusCode()).isEqualTo(SC_OK);
  }

  protected Response getHttpCall(String url) {
	return given()
		.log().all()
		.when()
		.get(url);
  }

}
