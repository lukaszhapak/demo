package com.example.demo.test.refactoring.archunit.movie.domain;

public interface MovieCreatedPublisher {

  void publishMovieCreatedEvent(Movie movie);

}
