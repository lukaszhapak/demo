package com.example.demo.spring.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class HelloController {

  @GetMapping("/hello")
  String hello() {
	log.debug("Get hello");
	return "Hello";
  }
}
