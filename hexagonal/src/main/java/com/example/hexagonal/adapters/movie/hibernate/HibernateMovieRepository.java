package com.example.hexagonal.adapters.movie.hibernate;

import com.example.hexagonal.domain.movie.MovieDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

interface HibernateMovieRepository extends Repository<HibernateMovie, Long> {

  Optional<HibernateMovie> findById(Long id);

  HibernateMovie save(HibernateMovie hibernateMovie);

  Long count();

  @Query("SELECT NEW com.example.hexagonal.domain.movie.MovieDTO(m.title, m.category) FROM HibernateMovie m")
  List<MovieDTO> findAll();
}
