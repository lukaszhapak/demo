package com.example.demo.test.unit.archunit.hexagonal.domain;

public interface MovieEventPublisher {

  void publishMovieCreatedEvent(Movie movie);

}
