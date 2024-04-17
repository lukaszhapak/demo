package com.example.demo.test.unit.archunit.hexagonal.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MovieService {

  private final MovieEventPublisher movieEventPublisher;

  Movie save(Movie movie) {
	movieEventPublisher.publishMovieCreatedEvent(movie);
	return movie;
  }

}
