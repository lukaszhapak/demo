package com.example.demo.spring.cache;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UUIDControllerTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  UUIDController UUIDController;

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
