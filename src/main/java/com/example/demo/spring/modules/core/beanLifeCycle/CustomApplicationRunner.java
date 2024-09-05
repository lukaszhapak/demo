package com.example.demo.spring.modules.core.beanLifeCycle;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CustomApplicationRunner implements ApplicationRunner {

  private final OrderService orderService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
	System.out.println("application runner");
	orderService.store("application runner");
  }
}
