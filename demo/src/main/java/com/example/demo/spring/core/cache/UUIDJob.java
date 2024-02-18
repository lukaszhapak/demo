package com.example.demo.spring.core.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class UUIDJob {

  private final UUIDService UUIDService;

  @Scheduled(fixedDelay = 10 * 60 * 1000)
  void scheduledEvictCached() {
	log.debug("Scheduled uuid cache evict");
	UUIDService.evictCached();
  }
}
