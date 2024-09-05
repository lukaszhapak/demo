package com.example.demo.spring.modules.core.aop;

import com.example.demo.spring.modules.core.aop.cache.CacheableAspect;
import com.example.demo.spring.modules.core.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AopConfiguration {

  @Bean
  LoggingAspect loggingAspect() {
	return new LoggingAspect();
  }

  @Bean
  CacheableAspect cacheableAspect() {
	return new CacheableAspect();
  }
}
