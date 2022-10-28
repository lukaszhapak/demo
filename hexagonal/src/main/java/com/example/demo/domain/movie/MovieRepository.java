package com.example.demo.domain.movie;

import java.util.Optional;

public interface MovieRepository {

  Optional<Movie> findById(Long id);
}
