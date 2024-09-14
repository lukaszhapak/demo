package com.example.demo.nonspring.threads.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AtomicDemo {

  static int counter = 0;
  static AtomicInteger atomicCounter = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
	for (int i = 0; i < 1000; i++) {
	  new Thread(() -> {
		try {
		  Thread.sleep(25);
		} catch (InterruptedException e) {
		  throw new RuntimeException(e);
		}
		counter++;
		atomicCounter.incrementAndGet();
	  } ).start();
	}

	Thread.sleep(500);

	log.debug("index = {}", counter);
	log.debug("atomic index = {}", atomicCounter);
  }

}
