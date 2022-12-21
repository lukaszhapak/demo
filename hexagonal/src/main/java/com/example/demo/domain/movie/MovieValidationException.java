package com.example.demo.domain.movie;

public class MovieValidationException extends RuntimeException {

  public MovieValidationException(String message) {
	super(message);
  }
}
