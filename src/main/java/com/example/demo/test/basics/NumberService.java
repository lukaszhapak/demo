package com.example.demo.test.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberService {

  public int returningInt(Integer number) {
	log.debug("returning int, number={}", number);
	return number;
  }

  public int returningInt() {
	log.debug("returning int");
	return 5;
  }

  public void returningVoid() {
	log.debug("returning void");
  }
}
