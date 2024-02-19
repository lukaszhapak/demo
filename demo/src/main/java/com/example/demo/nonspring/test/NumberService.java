package com.example.demo.nonspring.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NumberService {

  int returningInt(Integer number) {
	log.debug("returning int, number={}", number);
	return number;
  }

  int returningInt() {
	log.debug("returning int");
	return 5;
  }

  void returningVoid() {
	log.debug("returning void");
  }
}
