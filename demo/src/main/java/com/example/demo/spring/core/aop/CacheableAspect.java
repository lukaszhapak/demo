package com.example.demo.spring.core.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
class CacheableAspect {

  private final Map<String, Object> cache = new HashMap<>();

  @Around("@annotation(com.example.demo.spring.core.aop.Cacheable)")
  public Object checkCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	log.debug("checking cache for method={},  args={}", proceedingJoinPoint.toShortString(), proceedingJoinPoint.getArgs());

	String key = proceedingJoinPoint.toShortString() + Arrays.toString(proceedingJoinPoint.getArgs());
	if (cache.containsKey(key)) {
	  Object valueFromCache = cache.get(key);
	  log.debug("value present in cache value={}", valueFromCache);
	  return valueFromCache;
	}
	Object result = proceedingJoinPoint.proceed();
	log.debug("storing in cache value={}, key={}", result, key);
	cache.put(key, result);
	return result;
  }
}
