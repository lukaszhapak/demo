package com.example.demo.test.integration.gameEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
class GameEngineApplication {

  public static void main(String[] args) {
	SpringApplication.run(GameEngineApplication.class, args);
  }
}
