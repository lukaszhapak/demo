package com.example.demo.core.port;

import com.example.demo.core.domain.Movie;

import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findById(Long id);
}
