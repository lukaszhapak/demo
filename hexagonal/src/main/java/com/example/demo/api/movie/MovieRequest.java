package com.example.demo.api.movie;

import com.example.demo.domain.movie.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class MovieRequest {

  private String title;
  private String author;

  Movie toDomain() {
	Movie movie = new Movie();
	movie.setTitle(title);
	movie.setAuthor(author);
	return movie;
  }
}
