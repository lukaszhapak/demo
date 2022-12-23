package com.example.hexagonal.api.movie;

import com.example.hexagonal.domain.movie.MovieFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
class MovieEndpoint {

  private final MovieFacade movieFacade;

  @GetMapping("/api/movie/{id}")
  public MovieResponse findById(@PathVariable Long id) {
	log.trace("Find movie request id={}", id);
	MovieResponse movieResponse = MovieResponse.of(movieFacade.findById(id));
	log.trace("Find movie response movieResponse={}", movieResponse);
	return movieResponse;
  }

  @PostMapping("/api/movie")
  public MovieResponse createMovie(@RequestBody MovieRequest movieRequest) {
	log.trace("Create movie request movieRequest={}", movieRequest);
	MovieResponse movieResponse = MovieResponse.of(movieFacade.createMovie(movieRequest.toDomain()));
	log.trace("Create movie response movieResponse={}", movieResponse);
	return movieResponse;
  }
}
