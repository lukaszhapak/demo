package com.example.demo.nonspring.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class RestClientThread extends Thread {

  private final RestClient restClient;
  private int numberOfExecutions = 0;
  private boolean active = true;

  @Override
  public void run() {
	while (true) {
	  if (active && numberOfExecutions > 0) {
		restClient.getPosts();
		numberOfExecutions--;
	  } else {
		active = false;
	  }
	}
  }
}
