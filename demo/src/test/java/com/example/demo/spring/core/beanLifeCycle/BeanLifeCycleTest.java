package com.example.demo.spring.core.beanLifeCycle;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BeanLifeCycleTest extends AbstractIntegrationTest {


  @Autowired
  Service service;

  @Test
  @DisplayName("Test name")
  void testName() {
	// given

	// when

	// then
  }
}