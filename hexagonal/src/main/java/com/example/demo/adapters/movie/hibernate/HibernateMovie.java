package com.example.demo.adapters.movie.hibernate;

import com.example.demo.domain.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MOVIE")
class HibernateMovie {

  @Id
  @GeneratedValue
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

  static HibernateMovie of(Movie movie) {
	HibernateMovie hibernateMovie = new HibernateMovie();
	hibernateMovie.setId(movie.getId());
	hibernateMovie.setTitle(movie.getTitle());
	hibernateMovie.setAuthor(movie.getAuthor());
	return hibernateMovie;
  }
}
