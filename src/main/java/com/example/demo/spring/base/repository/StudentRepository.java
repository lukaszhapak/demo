package com.example.demo.spring.base.repository;

import com.example.demo.spring.base.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

