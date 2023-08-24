package com.example.batch.api.advice;

import com.example.batch.exception.SystemException;
import com.example.batch.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

  @ExceptionHandler(SystemException.class)
  ResponseEntity<String> handleSystemException(Exception exception) {
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<String> handleValidationException(Exception exception) {
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
