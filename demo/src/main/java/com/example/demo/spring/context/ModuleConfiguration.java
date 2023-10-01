package com.example.demo.spring.context;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
class ModuleConfiguration {

  @Bean
  StudentService studentService(StudentRepository studentRepository) {
	return new StudentService(studentRepository);
  }
}
