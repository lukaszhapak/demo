package com.example.hexagonal.domain.movie;

public class MovieValidationException extends RuntimeException {

  public MovieValidationException(String message) {
	super(message);
  }
}
