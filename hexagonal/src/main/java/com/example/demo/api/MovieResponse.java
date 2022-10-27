package com.example.demo.api;

import com.example.demo.core.domain.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MovieResponse {

    private Long id;
    private String title;
    private String author;

    static MovieResponse of(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setAuthor(movie.getAuthor());
        return movieResponse;
    }
}
