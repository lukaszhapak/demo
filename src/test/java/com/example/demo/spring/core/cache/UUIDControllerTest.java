package com.example.demo.spring.core.cache;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UUIDControllerTest extends AbstractIntegrationTest {

  @Autowired
  com.example.demo.spring.core.cache.UUIDController UUIDController;

  @Test
  @DisplayName("test cache")
  void testCache() {
	UUIDController.getCached();
	UUIDController.getCached();
	UUIDController.getCached();
	UUIDController.evictCached();
	UUIDController.getCached();
	UUIDController.getCached();
	UUIDController.getCached();
	UUIDController.getCached();
  }
}
