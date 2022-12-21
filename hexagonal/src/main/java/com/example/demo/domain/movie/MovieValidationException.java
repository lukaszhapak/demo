package com.example.demo.domain.movie;

public class MovieValidationException extends RuntimeException {

  public MovieValidationException() {
	super("Invalid Movie");
  }
}
