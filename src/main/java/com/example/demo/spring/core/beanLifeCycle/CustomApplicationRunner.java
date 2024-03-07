package com.example.demo.spring.core.beanLifeCycle;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class CustomApplicationRunner implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
	System.out.println("application runner");
  }
}
