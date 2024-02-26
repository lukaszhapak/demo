package com.example.demo.spring.core.beanLifeCycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class CustomCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
	System.out.println("command line runner");
  }
}
