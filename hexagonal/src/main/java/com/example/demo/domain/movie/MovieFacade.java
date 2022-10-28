package com.example.demo.domain.movie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieFacade {

  private final MovieStorage movieStorage;
  private final MovieCreatedEventSender movieCreatedEventSender;

  public Movie createMovie(Movie movie) {
	movieCreatedEventSender.send(MovieCreatedEvent.of(movie));
	return movieStorage.create(movie);
  }

  public Movie findById(Long id) {
	return movieStorage.findById(id)
		.orElseThrow(() -> new MovieNotFoundException(id));
  }
}
