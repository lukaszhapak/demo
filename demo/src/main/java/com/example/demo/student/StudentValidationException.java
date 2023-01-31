package com.example.demo.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class StudentValidationException extends RuntimeException{

  StudentValidationException(String message) {
	super(message);
  }
}
