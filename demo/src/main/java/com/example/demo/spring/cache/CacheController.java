package com.example.demo.spring.cache;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class CacheController {

  @Cacheable("UUID")
  @GetMapping("/cached")
  public String getCached() {
	log.debug("Get cached value request");
	return UUID.randomUUID().toString();
  }

  @CacheEvict("UUID")
  @GetMapping("/cached/evict")
  public String evictCached() {
	log.debug("Manual cache evict");
	return "Cache evicted";
  }

  @CacheEvict("UUID") // need public
  @Scheduled(fixedDelay = 10000) // not need public
  public void scheduledEvictCached() {
	log.debug("Scheuled cache evict");
  }
}
