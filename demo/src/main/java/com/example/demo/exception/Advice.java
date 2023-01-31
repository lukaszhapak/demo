package com.example.demo.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
class Advice {

  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<String> handleNotFound(Exception exception) {
	log.error(exception.getMessage());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<String> handleValidation(Exception exception) {
	log.error(exception.getMessage());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
