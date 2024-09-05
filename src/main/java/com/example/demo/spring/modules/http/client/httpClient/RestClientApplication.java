package com.example.demo.spring.modules.http.client.httpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
class RestClientApplication {

  public static void main(String[] args) {
	SpringApplication.run(RestClientApplication.class, args);
  }

  @Bean
  RestTemplate restTemplate() {
	return new RestTemplate();
  }
}
