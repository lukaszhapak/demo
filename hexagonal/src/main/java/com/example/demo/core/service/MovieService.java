package com.example.demo.core.service;

import com.example.demo.core.domain.Movie;
import com.example.demo.core.exception.MovieNotFoundException;
import com.example.demo.core.port.MovieRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }
}
