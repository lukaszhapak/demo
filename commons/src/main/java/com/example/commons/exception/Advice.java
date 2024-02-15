package com.example.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<String> handleNotFound(Exception exception) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<ValidationExceptionDTO> handleValidation(ValidationException exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
		new ValidationExceptionDTO(exception.getMessage(), exception.getInvalidFields()));
  }
}
