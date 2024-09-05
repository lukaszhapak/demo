package com.example.demo.spring.modules.http.server.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHandlerAdvice {

  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<String> notFound(NotFoundException e) {
	return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SystemException.class)
  ResponseEntity<String> system(SystemException e) {
	return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
