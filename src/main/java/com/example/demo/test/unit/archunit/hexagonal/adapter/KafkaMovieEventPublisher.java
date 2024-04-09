package com.example.demo.test.unit.archunit.hexagonal.adapter;

import com.example.demo.test.unit.archunit.hexagonal.domain.Movie;
import com.example.demo.test.unit.archunit.hexagonal.domain.MovieEventPublisher;

class KafkaMovieEventPublisher implements MovieEventPublisher {

  @Override
  public void publishMovieCreatedEvent(Movie movie) {

  }
}
