package com.example.batch.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class Advice {

  @ExceptionHandler(SystemException.class)
  ResponseEntity<String> handleSystemException(Exception exception) {
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<String> handleValidationException(Exception exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
