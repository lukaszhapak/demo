package com.example.demo.spring.http.server.handlerInterceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class StudentController {

  @GetMapping("/api/string")
  public String string() {
	return "Student";
  }
}
