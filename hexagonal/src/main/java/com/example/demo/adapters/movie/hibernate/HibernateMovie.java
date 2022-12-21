package com.example.demo.adapters.movie.hibernate;

import com.example.demo.domain.movie.Movie;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
@AllArgsConstructor
public class HibernateMovie {

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
