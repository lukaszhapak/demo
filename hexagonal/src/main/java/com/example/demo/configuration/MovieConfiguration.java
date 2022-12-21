package com.example.demo.configuration;

import com.example.demo.domain.movie.MovieCreatedEventPublisher;
import com.example.demo.domain.movie.MovieFacade;
import com.example.demo.domain.movie.MovieStorage;
import com.example.demo.domain.movie.MovieValidator;
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
