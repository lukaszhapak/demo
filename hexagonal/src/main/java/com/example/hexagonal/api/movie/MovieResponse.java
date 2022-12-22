package com.example.hexagonal.api.movie;

import com.example.hexagonal.domain.movie.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class MovieResponse {

  private Long id;
  private String title;
  private String author;

  static MovieResponse of(Movie movie) {
	MovieResponse movieResponse = new MovieResponse();
	movieResponse.setId(movie.getId());
	movieResponse.setTitle(movie.getTitle());
	movieResponse.setAuthor(movie.getAuthor());
	return movieResponse;
  }
}
