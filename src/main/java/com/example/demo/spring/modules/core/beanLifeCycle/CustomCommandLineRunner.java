package com.example.demo.spring.modules.core.beanLifeCycle;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CustomCommandLineRunner implements CommandLineRunner {

  private final OrderService orderService;

  @Override
  public void run(String... args) throws Exception {
	System.out.println("command line runner");
	orderService.store("command line runner");
  }
}
