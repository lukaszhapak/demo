package com.example.demo.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
	super(message);
	log.error(message);
  }
}
