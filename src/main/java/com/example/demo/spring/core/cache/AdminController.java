package com.example.demo.spring.core.cache;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AdminController {

  private final CacheManager cacheManager;

  @GetMapping("/cache/admin")
  public Map<String, Cache> getCaches() {
	Map<String, Cache> caches = new HashMap<>();
	cacheManager.getCacheNames().stream().forEach(name -> caches.put(name, cacheManager.getCache(name)));
	return caches;
  }
}
