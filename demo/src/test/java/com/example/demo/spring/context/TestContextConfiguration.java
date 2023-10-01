package com.example.demo.spring.context;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EntityScan
@EnableJpaRepositories
@AutoConfigureDataJpa
class TestContextConfiguration {

  @Bean
  StudentService studentService(StudentRepository studentRepository) {
	return new StudentService(studentRepository);
  }
}
