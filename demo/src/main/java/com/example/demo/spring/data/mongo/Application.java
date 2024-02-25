package com.example.demo.spring.data.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
class Application {

  public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
  }
}
