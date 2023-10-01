package com.example.demo.spring.context;

import com.example.demo.spring.context.ServiceForTestContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableJpaRepositories
@EnableAutoConfiguration
class TestContext {

  @Bean
  ServiceForTestContext serviceForTestContext() {
	return new ServiceForTestContext();
  }
}
