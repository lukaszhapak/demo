package com.example.demo.configuration;

import com.example.demo.core.port.MovieRepository;
import com.example.demo.core.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfiguration {

    @Bean
    MovieService movieService(final MovieRepository movieRepository) {
        return new MovieService(movieRepository);
    }
}
