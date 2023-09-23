package com.example.demo.query;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ChildRepository extends JpaRepository<Child, Long> {

  @Query("SELECT c from Child c LEFT JOIN FETCH c.parents p where c.id = :id")
  Optional<Child> findById(Long id);
}
