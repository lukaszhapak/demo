package com.example.demo.domain.movie;

import java.util.Optional;

public interface MovieStorage {

  Optional<Movie> findById(Long id);

  Movie create(Movie movie);
}
