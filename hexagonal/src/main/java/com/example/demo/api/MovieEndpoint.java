package com.example.demo.api;

import com.example.demo.core.port.MovieRepository;
import com.example.demo.core.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MovieEndpoint {

    private final MovieService movieService;

    @GetMapping("/api/movie/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        return MovieResponse.of(movieService.findById(id));
    }
}
