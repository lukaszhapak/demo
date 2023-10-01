package com.example.demo.spring.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class CacheController {

  private final CacheService cacheService;

  @GetMapping("/cached")
  public String getCached() {
	log.debug("Get cached value request");
	String cached = cacheService.getCached();
	log.debug("Get cached value={}", cached);
	return cached;
  }

  @GetMapping("/cached/evict")
  public String evictCached() {
	log.debug("Manual cache evict");
	return cacheService.evictCached();
  }
}
