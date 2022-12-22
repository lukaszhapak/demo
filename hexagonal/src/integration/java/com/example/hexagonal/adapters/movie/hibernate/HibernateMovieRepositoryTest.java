package com.example.hexagonal.adapters.movie.hibernate;

import com.example.hexagonal.AbstractIntegrationTest;
import com.example.hexagonal.domain.movie.MovieDTO;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class HibernateMovieRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private HibernateMovieRepository hibernateMovieRepository;

  @Test
  @DisplayName("should find dto")
  void shouldFindDto() {
    jdbc.execute(
        "insert into movie (id, title, author, category) values (1000000, 'smierc w wenecji', 'andrzej', 'COMEDY');");
	List<MovieDTO> all = hibernateMovieRepository.findAll();
  }

}