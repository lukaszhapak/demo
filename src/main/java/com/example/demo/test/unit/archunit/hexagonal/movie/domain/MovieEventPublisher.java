package com.example.demo.test.unit.archunit.hexagonal.movie.domain;

public interface MovieEventPublisher {

  void publishMovieCreatedEvent(Movie movie);

}
