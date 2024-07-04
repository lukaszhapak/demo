package com.example.demo.test.integration.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
class ProductApplication {

  public static void main(String[] args) {
	SpringApplication.run(ProductApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
	return new RestTemplate();
  }
}
