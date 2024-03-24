package com.example.demo.spring.tools.outbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
class OutboxApplication {

  public static void main(String[] args) {
	SpringApplication.run(OutboxApplication.class, args);
  }
}
