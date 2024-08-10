package com.example.demo.nonspring.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Service {

  public void execute(){
	log.debug("execute");
	try {
	  Thread.sleep(10);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
  }
}
