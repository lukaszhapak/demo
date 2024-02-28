package com.example.demo.spring.test.testContainer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AnotherTestContainerTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("Test name")
  void testName() {
	// given
    Student john = studentRepository.save(new Student(1L, "John", 24));

    // when

	// then
  }

}