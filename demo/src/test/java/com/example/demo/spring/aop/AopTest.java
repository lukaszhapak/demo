package com.example.demo.spring.aop;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AopTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;


  @Test
  @DisplayName("Test name")
  void testName() {
	// given

	// when
    studentService.getStudent(1);
    studentService.getStudent(2);
    studentService.getStudent(2);
    studentService.getStudent(3);

    // then
  }
}