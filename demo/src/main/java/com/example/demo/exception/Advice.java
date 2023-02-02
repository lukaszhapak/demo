package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class Advice {

  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<String> handleNotFound(Exception exception) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<InvalidFields> handleValidation(ValidationException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		new InvalidFields(exception.getMessage(), exception.getInvalidFields()));
  }
}
