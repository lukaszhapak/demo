package com.example.demo.test.refactoring.archunit.movie.adapter;

import com.example.demo.test.refactoring.archunit.movie.domain.Movie;
import com.example.demo.test.refactoring.archunit.movie.domain.MovieEventPublisher;

class KafkaMovieEventPublisher implements MovieEventPublisher {

  @Override
  public void publishMovieCreatedEvent(Movie movie) {

  }
}
