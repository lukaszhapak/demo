package com.example.demo.spring.context;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@EnableAutoConfiguration
class TestContextWithAutoConfiguration {

  @Bean
  StudentService studentService(StudentRepository studentRepository) {
	return new StudentService(studentRepository);
  }
}
