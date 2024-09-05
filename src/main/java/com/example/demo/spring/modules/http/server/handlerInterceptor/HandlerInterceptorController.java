package com.example.demo.spring.modules.http.server.handlerInterceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class HandlerInterceptorController {

  @GetMapping("/api/handler-interceptor/string")
  public String string() {
	return "Student";
  }
}
