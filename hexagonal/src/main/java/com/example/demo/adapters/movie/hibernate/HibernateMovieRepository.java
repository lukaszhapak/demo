package com.example.demo.adapters.movie.hibernate;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface HibernateMovieRepository extends Repository<HibernateMovie, Long> {

  Optional<HibernateMovie> findById(Long id);

  HibernateMovie save(HibernateMovie hibernateMovie);
}
