package com.example.demo.spring.modules.data.jpa.search.specification;

import com.example.demo.spring.base.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpecificationStudentRepository extends JpaRepository<Student, Long> {

  Page<Student> findAll(Specification<Student> specification, Pageable pageable);
}
