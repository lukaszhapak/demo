package com.example.demo.nonspring.threads.volatileDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class VolatileMain {

  volatile static int index = 0;

  public static void main(String[] args) throws InterruptedException {

	Thread t1 = new Thread(() -> {
	  int localIndex = 0;
	  while (index < 10) {
		if (localIndex != index) {
		  log.debug("index = {}", index);
		  localIndex = index;
		}
	  }
	});

	Thread t2 = new Thread(() -> {
	  for (int i = 0; i < 10; i++) {
		try {
		  Thread.sleep(500);
		} catch (InterruptedException e) {
		  throw new RuntimeException(e);
		}
		log.debug("increment to={}", ++index);
	  }
	});

	t1.start();
	t2.start();
  }
}
