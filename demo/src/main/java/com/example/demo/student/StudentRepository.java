package com.example.demo.student;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long> {

  Optional<Student> findStudentById(Long id);

  Student save(Student student);

  Long count();
}
