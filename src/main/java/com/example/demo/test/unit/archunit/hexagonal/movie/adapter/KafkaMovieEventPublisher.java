package com.example.demo.test.unit.archunit.hexagonal.movie.adapter;

import com.example.demo.test.unit.archunit.hexagonal.movie.domain.Movie;
import com.example.demo.test.unit.archunit.hexagonal.movie.domain.MovieEventPublisher;

class KafkaMovieEventPublisher implements MovieEventPublisher {

  @Override
  public void publishMovieCreatedEvent(Movie movie) {

  }
}
