package com.example.demo.spring.cache;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class CacheService {

  @Cacheable("UUID")
  public String getCached() {
	log.debug("Get cached value");
	return UUID.randomUUID().toString();
  }

  @CacheEvict("UUID")
  public String evictCached() {
	log.debug("Evict cache");
	return "Cache evicted";
  }
}
