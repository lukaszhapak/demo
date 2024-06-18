package com.example.demo.spring.http.server.exceptionHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class StudentController {

  @GetMapping("/api/404")
  public String notFound() {
	throw new StudentNotFoundException();
  }

  @GetMapping("/api/500")
  public String systemError() {
	throw new SystemException();
  }
}
