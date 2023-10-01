package com.example.demo.nonspring.threads;

class Main {

  public static final int numberOfThreads = 10;
  public static final int NUMBER_OF_EXECUTIONS = 1000;

  public static void main(String[] args) {
	for (int i = 0; i < numberOfThreads; i++) {
	  Thread thread = new MyThread(new Service(), NUMBER_OF_EXECUTIONS);
	  thread.start();
	}
  }
}
