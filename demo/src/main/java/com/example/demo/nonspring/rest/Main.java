package com.example.demo.nonspring.rest;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Main {

  public static final List<RestClientThread> THREADS = new ArrayList<>();
  public static int numberOfCalls = 100;

  public static void main(String[] args) {
	createThreads();
	startThreads();
	insertCalls();
  }

  private static void insertCalls() {
	while (numberOfCalls > 0) {
	  for (RestClientThread thread : THREADS) {
		int numberOfExecutions = thread.getNumberOfExecutions();
		thread.setNumberOfExecutions(numberOfExecutions + 1);
		thread.setActive(true);
		numberOfCalls--;
	  }
	}
  }

  private static void createThreads() {
	for (int i = 0; i < 10; i++) {
	  THREADS.add(new RestClientThread(new RestClient()));
	}
  }

  private static void startThreads() {
	for (RestClientThread thread : THREADS) {
	  thread.start();
	}
  }
}
