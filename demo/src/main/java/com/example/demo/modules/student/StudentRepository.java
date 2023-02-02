package com.example.demo.modules.student;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<StudentEntity, Long> {

  Optional<StudentEntity> findStudentById(Long id);

  StudentEntity save(StudentEntity studentEntity);

  Long count();

  Long deleteAllById(Long id);

  boolean existsById(Long id);

  @Query("select s.grades from StudentEntity s")
  List<String> getStudentGrades(Specification<StudentEntity> specification);

  List<StudentEntity> findAll(Specification<StudentEntity> specification);
}
