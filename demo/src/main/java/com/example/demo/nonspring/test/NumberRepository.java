package com.example.demo.nonspring.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NumberRepository {

  int returningInt(){
	log.debug("returning int");
	return 255;
  }
}