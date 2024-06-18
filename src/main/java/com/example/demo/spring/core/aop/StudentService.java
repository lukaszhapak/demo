package com.example.demo.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class StudentService {

  @Cacheable
  String getStudentCacheableAspect(Integer id) {
	if (id % 2 == 0) {
	  return "John";
	}
	return "Brian";
  }

  @Logging
  String getStudentLoggingAspect(Integer id) {
	try {
	  Thread.sleep(154);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
	return "John";
  }
}
