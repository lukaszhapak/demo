package com.example.hexagonal.adapters.movie.hibernate;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.domain.movie.MovieStorage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "movie.storage", name = "type", havingValue = "hibernate")
class HibernateMovieStorage implements MovieStorage {

  private final HibernateMovieRepository hibernateMovieRepository;

  @Override
  public Optional<Movie> findById(Long id) {
	return hibernateMovieRepository.findById(id).map(HibernateMovie::toMovie);
  }

  @Override
  public Movie create(Movie movie) {
	return hibernateMovieRepository.save(HibernateMovie.of(movie)).toMovie();
  }

  @Override
  public Long count() {
	return hibernateMovieRepository.count();
  }
}
