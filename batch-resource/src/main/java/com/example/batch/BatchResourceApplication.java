package com.example.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BatchResourceApplication {

  public static void main(String[] args) {
	SpringApplication.run(BatchResourceApplication.class, args);
  }

}
