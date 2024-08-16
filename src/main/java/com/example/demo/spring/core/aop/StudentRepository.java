package com.example.demo.spring.core.aop;

import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<Student, Long> {

  Student save(Student student);

  Student findByName(String name);

  Student findByNameAndAge(String name, int age);
}
