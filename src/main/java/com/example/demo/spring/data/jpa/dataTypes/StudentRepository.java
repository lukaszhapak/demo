package com.example.demo.spring.data.jpa.dataTypes;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("Select s from Student s LEFT JOIN FETCH s.oneToMany where s.id = :id")
  Optional<Student> findByIdFetchingOneToMany(Long id);
}
