package com.example.demo.test.refactoring.archunit.movie.adapter;

import com.example.demo.test.refactoring.archunit.movie.domain.Movie;
import com.example.demo.test.refactoring.archunit.movie.domain.MovieCreatedPublisher;

class KafkaMovieCreatedPublisher implements MovieCreatedPublisher {

  @Override
  public void publishMovieCreatedEvent(Movie movie) {

  }
}
