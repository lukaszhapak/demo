package com.example.demo.spring.message.internalEvent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
class InternalEventApplication {

  public static void main(String[] args) {
	SpringApplication.run(InternalEventApplication.class, args);
  }
}
