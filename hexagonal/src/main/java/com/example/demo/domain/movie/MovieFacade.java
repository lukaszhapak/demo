package com.example.demo.domain.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class MovieFacade {

  private final MovieStorage movieStorage;
  private final MovieCreatedEventSender movieCreatedEventSender;

  public Movie createMovie(Movie movie) {
	log.debug("create movie Movie={}", movie);
	Movie response = movieStorage.create(movie);
	movieCreatedEventSender.send(MovieCreatedEvent.of(movie));
	return response;
  }

  public Movie findById(Long id) {
	log.debug("find movie by id={}", id);
	return movieStorage.findById(id)
		.orElseThrow(() -> new MovieNotFoundException(id));
  }
}
