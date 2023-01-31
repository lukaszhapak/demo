package com.example.demo.student;

import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long> {

  Student findStudentById(Long id);

  Student save(Student student);

  Long count();
}
