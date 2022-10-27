package com.example.demo.adapters.repository;

import com.example.demo.core.domain.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "movie")
class JpaMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    Movie toMovie() {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setAuthor(author);
        return movie;
    }
}
