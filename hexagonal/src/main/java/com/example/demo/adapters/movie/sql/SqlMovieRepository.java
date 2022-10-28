package com.example.demo.adapters.movie.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMovieRepository extends JpaRepository<SqlMovie, Long> {

}
