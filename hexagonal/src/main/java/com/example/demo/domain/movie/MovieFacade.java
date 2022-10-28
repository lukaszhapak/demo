package com.example.demo.domain.movie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieFacade {

  private final MovieStorage movieStorage;

  public Movie findById(Long id) {
	return movieStorage.findById(id)
		.orElseThrow(() -> new MovieNotFoundException(id));
  }
}
