package com.example.hexagonal.domain.movie;

public class MovieValidator {

  public void validate(Movie movie) {
	validateString(movie.getTitle(), 2, 64, "Title");
	validateString(movie.getAuthor(), 2, 64, "Author");
	validateCategory(movie.getCategory());
  }

  private void validateString(String string, Integer min, Integer max, String name) {
	if (string == null) {
	  throw new MovieValidationException(name + " cannot be null");
	}
	if (string.length() < min) {
	  throw new MovieValidationException(name + " length cannot be less than 2 characters");
	}
	if (string.length() > max) {
	  throw new MovieValidationException(name + " length cannot be more than 64 characters");
	}
  }

  private void validateCategory(MovieCategory category) {
	if (category == null) {
	  throw new MovieValidationException("category cannot be null");
	}
  }
}
