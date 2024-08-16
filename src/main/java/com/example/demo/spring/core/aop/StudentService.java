package com.example.demo.spring.core.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@LoggingBeforeExecution
@LoggingAfterExecution
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  @Cacheable
  Student findById(Integer id) {
	if (id % 2 == 0) {
	  return new Student().setName("John");
	}
	return new Student().setName("Brian");
  }

  Student save(Student student) {
	return studentRepository.save(student);
  }

  Student findByName(String name) {
	return studentRepository.findByName(name);
  }

  Student findByNameAndAge(String name, int age) {
	return studentRepository.findByNameAndAge(name, age);
  }

  void publishEvent(Student student) {
  }

  String noArgumentsMethod() {
	return "student";
  }
}
