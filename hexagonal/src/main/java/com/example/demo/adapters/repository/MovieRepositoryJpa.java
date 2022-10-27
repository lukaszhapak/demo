package com.example.demo.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MovieRepositoryJpa extends JpaRepository<JpaMovie, Long> {
}
