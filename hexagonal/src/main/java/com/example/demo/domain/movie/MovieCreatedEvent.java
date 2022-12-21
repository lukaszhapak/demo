package com.example.demo.domain.movie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieCreatedEvent {

  private Long id;
  private String title;
  private String author;

  static MovieCreatedEvent of(Movie movie) {
	MovieCreatedEvent movieCreatedEvent = new MovieCreatedEvent();
	movieCreatedEvent.setId(movie.getId());
	movieCreatedEvent.setTitle(movie.getTitle());
	movieCreatedEvent.setAuthor(movie.getAuthor());
	return movieCreatedEvent;
  }
}
