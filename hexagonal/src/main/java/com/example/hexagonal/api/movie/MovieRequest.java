package com.example.hexagonal.api.movie;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.domain.movie.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class MovieRequest {

  private String title;
  private String author;
  private MovieCategory category;

  Movie toDomain() {
	return new Movie(
		null,
		title,
		author,
		category
	);
  }
}
