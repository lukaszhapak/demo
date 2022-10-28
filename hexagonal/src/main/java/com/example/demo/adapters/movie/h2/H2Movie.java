package com.example.demo.adapters.movie.h2;

import com.example.demo.domain.movie.Movie;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MOVIE")
class H2Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String author;

  Movie toMovie() {
	Movie movie = new Movie();
	movie.setId(id);
	movie.setTitle(title);
	movie.setAuthor(author);
	return movie;
  }

  static H2Movie of(Movie movie) {
	H2Movie h2Movie = new H2Movie();
	h2Movie.setId(movie.getId());
	h2Movie.setTitle(movie.getTitle());
	h2Movie.setAuthor(movie.getAuthor());
	return h2Movie;
  }
}
