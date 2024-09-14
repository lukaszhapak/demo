package com.example.demo.nonspring.threads.thread;

class ThreadMain {

  public static void main(String[] args) {

	for (int i = 0; i < 10; i++) {
	  Thread myThread = new MyThread();
	  myThread.start();
	}
  }
}
