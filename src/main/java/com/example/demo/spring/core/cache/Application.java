package com.example.demo.spring.core.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
class Application {

  public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
  }
}
