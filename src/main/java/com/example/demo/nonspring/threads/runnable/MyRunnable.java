package com.example.demo.nonspring.threads.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MyRunnable implements Runnable {

  @Override
  public void run() {
	try {
	  Thread.sleep(500);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
	log.debug("executing some operation");
  }
}
