package com.example.demo.test.integration.product.http.server;

import com.example.demo.test.integration.product.service.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorDTO> exception(ValidationException exception) {
	log.error("Exception caught ={}", exception.getMessage(), exception);
	return new ResponseEntity<>(new ErrorDTO(exception), HttpStatus.BAD_REQUEST);
  }
}
