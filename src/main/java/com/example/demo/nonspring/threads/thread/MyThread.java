package com.example.demo.nonspring.threads.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MyThread extends Thread {

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
