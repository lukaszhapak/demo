package com.example.demo.test.unit.testingApproach.behavior;

interface StudentRepository {

  Student save(Student student);

  boolean existsByName(String name);
}
