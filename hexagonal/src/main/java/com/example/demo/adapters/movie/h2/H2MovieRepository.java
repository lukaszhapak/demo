package com.example.demo.adapters.movie.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface H2MovieRepository extends JpaRepository<H2Movie, Long> {

}
