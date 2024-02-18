package com.example.demo.spring.core.beanProcessor;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BeanProcessorTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  StudentService studentServiceImpl;

  @Test
  @DisplayName("Test name")
  void testName() {
	// given

	// when
	studentServiceImpl.addStudent("John", 24);

	// then
  }

}