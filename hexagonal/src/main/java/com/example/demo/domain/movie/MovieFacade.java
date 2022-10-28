package com.example.demo.domain.movie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieFacade {

  private final MovieRepository movieRepository;

  public Movie findById(Long id) {
	return movieRepository.findById(id)
		.orElseThrow(() -> new MovieNotFoundException(id));
  }
}
