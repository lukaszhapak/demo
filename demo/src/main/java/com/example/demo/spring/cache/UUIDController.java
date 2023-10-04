package com.example.demo.spring.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class UUIDController {

  private final UUIDService UUIDService;

  @GetMapping("/cache/uuid")
  public String getCached() {
	log.debug("Get cached uuid value request");
	String cached = UUIDService.getCached();
	log.debug("Get cached uuid value={}", cached);
	return cached;
  }

  @GetMapping("/cache/uuid/evict")
  public String evictCached() {
	log.debug("Manual uuid cache evict");
	return UUIDService.evictCached();
  }
}
