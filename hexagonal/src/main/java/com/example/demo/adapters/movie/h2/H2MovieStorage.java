package com.example.demo.adapters.movie.h2;

import com.example.demo.domain.movie.Movie;
import com.example.demo.domain.movie.MovieStorage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "movie.storage", name = "type", havingValue = "h2")
class H2MovieStorage implements MovieStorage {

  private final H2MovieRepository h2MovieRepository;

  @Override
  public Optional<Movie> findById(Long id) {
	return h2MovieRepository.findById(id).map(H2Movie::toMovie);
  }
}
