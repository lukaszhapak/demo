package com.example.hexagonal.configuration;

import com.example.hexagonal.domain.movie.MovieCreatedEventPublisher;
import com.example.hexagonal.domain.movie.MovieFacade;
import com.example.hexagonal.domain.movie.MovieStorage;
import com.example.hexagonal.domain.movie.MovieValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MovieConfiguration {

  @Bean
  MovieFacade movieFacade(final MovieStorage movieStorage,
	  final MovieCreatedEventPublisher movieCreatedEventPublisher,
	  final MovieValidator movieValidator) {
	return new MovieFacade(movieStorage, movieCreatedEventPublisher, movieValidator);
  }

  @Bean
  MovieValidator movieValidator() {
	return new MovieValidator();
  }
}
