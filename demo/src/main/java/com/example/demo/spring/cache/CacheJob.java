package com.example.demo.spring.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class CacheJob {

  private final CacheService cacheService;

  @Scheduled(fixedDelay = 10000)
  void scheduledEvictCached() {
	log.debug("Scheuled cache evict");
	cacheService.evictCached();
  }
}
