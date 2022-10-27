package com.example.demo.adapters.repository;

import com.example.demo.core.domain.Movie;
import com.example.demo.core.port.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class MovieRepositoryImpl implements MovieRepository {

    private final MovieRepositoryJpa movieRepositoryJpa;

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepositoryJpa.findById(id).map(JpaMovie::toMovie);
    }
}
