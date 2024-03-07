package com.example.demo.spring.data.criteriaQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {

  Page<Student> findAll(Specification<Student> specification, Pageable pageable);
}
