package com.example.demo.spring.core.cache;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class UUIDService {

  @Cacheable(value = "uuid", cacheManager = "uuidCacheManager")
  public String getCached() {
	log.debug("Get cached uuid value");
	return UUID.randomUUID().toString();
  }

//  manual cache eviction no ttl is defined
  @CacheEvict(value = "uuid", cacheManager = "uuidCacheManager")
  public String evictCached() {
	log.debug("Evict uuid cache");
	return "Cache uuid evicted";
  }
}
