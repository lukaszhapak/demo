package com.example.demo.adapters.movie.sql;

import com.example.demo.domain.movie.Movie;
import com.example.demo.domain.movie.MovieRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class SqlMovieRepositoryImplementacja implements MovieRepository {

  private final SqlMovieRepository sqlMovieRepository;

  @Override
  public Optional<Movie> findById(Long id) {
	return sqlMovieRepository.findById(id).map(SqlMovie::toMovie);
  }
}
