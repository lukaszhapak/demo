package com.example.demo.api.movie;

import com.example.demo.domain.movie.MovieFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MovieEndpoint {

  private final MovieFacade movieFacade;

  @GetMapping("/api/movie/{id}")
  public MovieResponse findById(@PathVariable Long id) {
	return MovieResponse.of(movieFacade.findById(id));
  }

  @PostMapping("/api/movie")
  public MovieResponse createMovie(@RequestBody MovieRequest movieRequest) {
	return MovieResponse.of(movieFacade.createMovie(movieRequest.toDomain()));
  }
}
