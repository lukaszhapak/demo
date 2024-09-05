package com.example.demo.spring.modules.data.jpa.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface MappingsStudentRepository extends JpaRepository<MappingsStudent, Long> {

  @Query("Select s from MappingsStudent s LEFT JOIN FETCH s.oneToMany where s.id = :id")
  Optional<MappingsStudent> findByIdFetchingOneToMany(Long id);
}
