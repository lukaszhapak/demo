package com.example.demo.spring.outbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
class Application {

  public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
  }
}
