package com.example.demo.adapters.movie.h2;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface H2MovieRepository extends Repository<H2Movie, Long> {

  Optional<H2Movie> findById(Long id);

  H2Movie save(H2Movie h2Movie);
}
