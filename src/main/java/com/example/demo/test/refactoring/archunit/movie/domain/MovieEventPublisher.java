package com.example.demo.test.refactoring.archunit.movie.domain;

public interface MovieEventPublisher {

  void publishMovieCreatedEvent(Movie movie);

}
