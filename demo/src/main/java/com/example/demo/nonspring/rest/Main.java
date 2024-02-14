package com.example.demo.nonspring.rest;

import java.util.List;

class Main {

  public static void main(String[] args) {
	PostsRestClient postsRestClient = new PostsRestClient();
	List<Post> posts = postsRestClient.getPosts();
	postsRestClient.getPost(4);
  }
}
