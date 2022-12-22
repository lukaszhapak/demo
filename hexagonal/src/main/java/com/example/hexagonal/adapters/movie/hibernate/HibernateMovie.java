package com.example.hexagonal.adapters.movie.hibernate;

import static javax.persistence.EnumType.STRING;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.domain.movie.MovieCategory;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
class HibernateMovie {

  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String author;
  @Enumerated(STRING)
  private MovieCategory category;

  Movie toMovie() {
	return new Movie(
		id,
		title,
		author,
		category
	);
  }

  static HibernateMovie of(Movie movie) {
	return new HibernateMovie(
		movie.getId(),
		movie.getTitle(),
		movie.getAuthor(),
		movie.getCategory()
	);
  }
}
