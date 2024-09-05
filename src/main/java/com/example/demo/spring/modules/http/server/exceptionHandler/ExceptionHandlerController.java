package com.example.demo.spring.modules.http.server.exceptionHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ExceptionHandlerController {

  @GetMapping("/api/404")
  public String notFound() {
	throw new NotFoundException();
  }

  @GetMapping("/api/500")
  public String systemError() {
	throw new SystemException();
  }
}
