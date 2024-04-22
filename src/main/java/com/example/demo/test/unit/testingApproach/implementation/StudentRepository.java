package com.example.demo.test.unit.testingApproach.implementation;

interface StudentRepository {
  Student save(Student student);
  boolean existsByName(String name);
}
