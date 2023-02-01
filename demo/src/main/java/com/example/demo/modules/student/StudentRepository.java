package com.example.demo.modules.student;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<StudentEntity, Long> {

  Optional<StudentEntity> findStudentById(Long id);

  StudentEntity save(StudentEntity studentEntity);

  Long count();

  Long deleteAllById(Long id);
}
