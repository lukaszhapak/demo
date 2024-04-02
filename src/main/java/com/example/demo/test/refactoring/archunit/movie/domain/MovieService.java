package com.example.demo.test.refactoring.archunit.movie.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MovieService {

  private final MovieEventPublisher movieEventPublisher;

  Movie createMovie(Movie movie) {
	movieEventPublisher.publishMovieCreatedEvent(movie);
	return movie;
  }

}
