package com.example.demo.context;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories
@EnableAutoConfiguration
class TestContext {

  @Bean
  ServiceForTestContext serviceForTestContext() {
	return new ServiceForTestContext();
  }
}
