package com.example.demo.configuration;

import com.example.demo.domain.movie.MovieFacade;
import com.example.demo.domain.movie.MovieStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfiguration {

  @Bean
  MovieFacade movieFacade(final MovieStorage movieStorage) {
	return new MovieFacade(movieStorage);
  }
}
