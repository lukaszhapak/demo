package com.example.demo.spring.core.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
class AopApplication {

  public static void main(String[] args) {
	SpringApplication.run(AopApplication.class, args);
  }

  @Bean
  LoggingAspect loggingAspect() {
	return new LoggingAspect();
  }

  @Bean
  CacheableAspect cacheableAspect() {
	return new CacheableAspect();
  }
}
