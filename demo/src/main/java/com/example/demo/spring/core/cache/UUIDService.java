package com.example.demo.spring.core.cache;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class UUIDService {

  @Cacheable("UUID")
  public String getCached() {
	log.debug("Get cached uuid value");
	return UUID.randomUUID().toString();
  }

  @CacheEvict("UUID")
  public String evictCached() {
	log.debug("Evict uuid cache");
	return "Cache uuid evicted";
  }
}
