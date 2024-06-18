package com.example.demo.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class StudentService {

  @Cacheable
  Student get(Integer id) {
	if (id % 2 == 0) {
	  return new Student().setName("John");
	}
	return new Student().setName("Brian");
  }

  @Logging
  Student save(Student student) {
	return student;
  }
}
