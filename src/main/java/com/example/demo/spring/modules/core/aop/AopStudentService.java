package com.example.demo.spring.modules.core.aop;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.repository.StudentRepository;
import com.example.demo.spring.modules.core.aop.cache.Cacheable;
import com.example.demo.spring.modules.core.aop.logging.LoggingAfterExecution;
import com.example.demo.spring.modules.core.aop.logging.LoggingBeforeExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@LoggingBeforeExecution
@LoggingAfterExecution
@RequiredArgsConstructor
class AopStudentService {

  private final StudentRepository studentRepository;

  @Cacheable
  Student findById(Integer id) {
	if (id % 2 == 0) {
	  return new Student().setFirstName("John");
	}
	return new Student().setFirstName("Brian");
  }

  Student save(Student student) {
	return studentRepository.save(student);
  }

//  Student findByName(String name) {
//	return studentRepository.findByName(name);
//  }
//
//  Student findByNameAndAge(String name, int age) {
//	return studentRepository.findByNameAndAge(name, age);
//  }

  void publishEvent(Student student) {
  }

  String noArgumentsMethod() {
	return "student";
  }
}
