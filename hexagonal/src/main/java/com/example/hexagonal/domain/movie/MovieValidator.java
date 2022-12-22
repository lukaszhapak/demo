package com.example.hexagonal.domain.movie;

public class MovieValidator {

  public void validate(Movie movie) {
	validateTitle(movie.getTitle());
	validateAuthor(movie.getAuthor());
  }

  private void validateTitle(String title) {
	if (title == null) {
	  throw new MovieValidationException("title cannot be null");
	}
	if (title.length() < 2) {
	  throw new MovieValidationException("title length cannot be less than 2 characters");
	}
	if (title.length() > 64) {
	  throw new MovieValidationException("title length cannot be more than 64 characters");
	}
  }

  private void validateAuthor(String author) {
	if (author == null) {
	  throw new MovieValidationException("author cannot be null");
	}
	if (author.length() < 2) {
	  throw new MovieValidationException("author length cannot be less than 2 characters");
	}
	if (author.length() > 64) {
	  throw new MovieValidationException("author length cannot be more than 64 characters");
	}
  }
}
