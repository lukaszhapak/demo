package com.example.demo.spring.jpql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select student.name from Student student where student.id = :id")
    String selectName(Long id);
}
