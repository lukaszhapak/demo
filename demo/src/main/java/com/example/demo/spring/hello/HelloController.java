package com.example.demo.spring.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

  @GetMapping("/hello")
  String hello() {
	return "Hello";
  }
}
