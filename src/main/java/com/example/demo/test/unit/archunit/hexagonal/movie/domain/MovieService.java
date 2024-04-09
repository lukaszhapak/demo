package com.example.demo.test.unit.archunit.hexagonal.movie.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MovieService {

  private final MovieEventPublisher movieEventPublisher;

  Movie createMovie(Movie movie) {
	movieEventPublisher.publishMovieCreatedEvent(movie);
	return movie;
  }

}
