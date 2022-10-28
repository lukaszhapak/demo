package com.example.demo.adapters.movie.memory;

import com.example.demo.domain.movie.Movie;
import com.example.demo.domain.movie.MovieStorage;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "movie.storage", name = "type", havingValue = "memory")
class MemoryMovieStorage implements MovieStorage {

  HashMap<Long, MemoryMovie> map = new HashMap<>();

  @Override
  public Optional<Movie> findById(Long id) {
	return Optional.ofNullable(map.get(id))
		.map(MemoryMovie::toMovie);
  }

  @Override
  public Movie create(Movie movie) {
	return null;
  }
}
