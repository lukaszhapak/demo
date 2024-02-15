package com.example.demo.nonspring.rest;

import com.example.demo.commons.RestAssuredRestClient;
import io.restassured.response.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class PostsRestClient implements RestAssuredRestClient {

  public List<Post> getPosts() {
	log.debug("getting posts");
	Response response = getHttpCall("https://jsonplaceholder.typicode.com/posts/");
	List<Post> posts = List.of(response.getBody().as(Post[].class));
	log.debug("List of posts={}", posts);
	return posts;
  }

  public Post getPost(int id) {
	log.debug("getting post, i={}", id);
	Response response = getHttpCall("https://jsonplaceholder.typicode.com/posts/" + id);
	Post post = response.getBody().as(Post.class);
	log.debug("post={}", post);
	return post;
  }
}
