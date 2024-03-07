package com.example.demo.spring.core.beanLifeCycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
class Application {

  public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
  }

  @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
  Service service() {
	return new Service();
  }

}
