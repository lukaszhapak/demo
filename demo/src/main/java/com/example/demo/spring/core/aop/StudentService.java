package com.example.demo.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class StudentService {

  @Cacheable
  String getStudent(Integer id) {
	log.debug("get student id={}", id);
	if (id % 2 == 0){
	  return "John";
	}
	return "Brian";
  }
}
