package com.example.demo.nonspring.threads.synchronizedDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SynchronizedMain {

  static Integer counter = 0;

  public static void main(String[] args) throws InterruptedException {
	for (int i = 0; i < 1000; i++) {
	  new Thread(() -> {
		try {
		  Thread.sleep(25);
		} catch (InterruptedException e) {
		  throw new RuntimeException(e);
		}
		increment();
	  }).start();
	}
	Thread.sleep(500);
	log.debug("counter={}", counter);
  }

  private synchronized static void increment() {
	log.debug("incrementing to={}", counter);
	counter++;
  }
}
