package com.example.demo.test.refactoring.archunit.movie.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MovieService {

  private final MovieCreatedPublisher movieCreatedPublisher;

  Movie createMovie(Movie movie) {
	movieCreatedPublisher.publishMovieCreatedEvent(movie);
	return movie;
  }

}
