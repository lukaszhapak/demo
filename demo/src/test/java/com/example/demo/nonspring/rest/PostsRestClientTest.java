package com.example.demo.nonspring.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostsRestClientTest {

  @Test
  @DisplayName("should get posts")
  void shouldGetPosts() {
	// given
	PostsRestClient postsRestClient = new PostsRestClient();

	// when
	List<Post> posts = postsRestClient.getPosts();
	Post post = postsRestClient.getPost(4);
  }
}