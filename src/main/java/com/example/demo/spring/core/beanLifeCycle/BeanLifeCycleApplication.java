package com.example.demo.spring.core.beanLifeCycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
class BeanLifeCycleApplication {

  public static void main(String[] args) {
	SpringApplication.run(BeanLifeCycleApplication.class, args);
  }

  @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
  Service service(OrderService orderService) {
	return new Service(orderService);
  }

}
