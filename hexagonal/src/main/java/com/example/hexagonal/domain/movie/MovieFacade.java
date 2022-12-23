package com.example.hexagonal.domain.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class MovieFacade {

  private final MovieStorage movieStorage;
  private final MovieCreatedEventPublisher movieCreatedEventPublisher;
  private final MovieValidator movieValidator;

  public Movie createMovie(Movie movie) {
	log.debug("Create movie Movie={}", movie);
	movieValidator.validate(movie);
	Movie response = movieStorage.create(movie);
	movieCreatedEventPublisher.send(MovieCreatedEvent.of(response));
	return response;
  }

  public Movie findById(Long id) {
	log.trace("Find movie by id={}", id);
	return movieStorage.findById(id)
		.orElseThrow(() -> new MovieNotFoundException(id));
  }

  public Long count() {
	return movieStorage.count();
  }
}
