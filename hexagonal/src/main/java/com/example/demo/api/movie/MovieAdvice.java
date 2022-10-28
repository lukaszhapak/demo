package com.example.demo.api.movie;

import com.example.demo.domain.movie.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class MovieAdvice {

  @ExceptionHandler(MovieNotFoundException.class)
  ResponseEntity<String> handleNotFound(Exception exception) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
