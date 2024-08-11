package com.example.demo.spring.core.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
class CacheApplication {

  public static void main(String[] args) {
	SpringApplication.run(CacheApplication.class, args);
  }

  // when using third party cache provider more options are available to configure
  // when defining multiple cache managers one of them need to be defined as primary and cache manager needs to be referred in cache annotation
  @Bean
  @Primary
  CacheManager studentCacheManager() {
	CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("student");
	caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
		.expireAfterWrite(30, TimeUnit.SECONDS)
		.maximumSize(50));
	return caffeineCacheManager;
  }

  @Bean
  CacheManager uuidCacheManager() {
	CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("uuid");
	caffeineCacheManager.setCaffeine(Caffeine.newBuilder());
	return caffeineCacheManager;
  }
}
