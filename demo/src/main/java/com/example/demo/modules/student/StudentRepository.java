package com.example.demo.modules.student;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<StudentEntity, Long> {

  Optional<StudentEntity> findStudentById(Long id);

  StudentEntity save(StudentEntity studentEntity);

  Long count();

  Long deleteAllById(Long id);

  boolean existsById(Long id);

  Page<StudentEntity> findAll(Specification<StudentEntity> specification, Pageable pageable);
}
