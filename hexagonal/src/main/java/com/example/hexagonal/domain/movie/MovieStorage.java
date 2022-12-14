package com.example.hexagonal.domain.movie;

import java.util.Optional;

public interface MovieStorage {

  Optional<Movie> findById(Long id);

  Movie create(Movie movie);

  Long count();
}
