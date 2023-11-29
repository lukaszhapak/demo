package com.example.demo.nonspring.mockito;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Service {

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
