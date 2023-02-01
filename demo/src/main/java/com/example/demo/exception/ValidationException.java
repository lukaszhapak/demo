package com.example.demo.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidationException extends RuntimeException {

  public ValidationException(String message) {
	super(message);
	log.error(message);
  }
}
