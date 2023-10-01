package com.example.demo.spring.cache;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CacheControllerTest extends AbstractIntegrationTest {

  @Autowired
  CacheController cacheController;

  @Test
  @DisplayName("test cache")
  void testCache() {
	cacheController.getCached();
	cacheController.getCached();
	cacheController.getCached();
	cacheController.evictCached();
	cacheController.getCached();
	cacheController.getCached();
	cacheController.getCached();
	cacheController.getCached();

  }
}
