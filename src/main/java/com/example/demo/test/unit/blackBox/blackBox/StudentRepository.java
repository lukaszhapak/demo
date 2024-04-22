package com.example.demo.test.unit.blackBox.blackBox;

interface StudentRepository {

  Student save(Student student);

  Student findByName(String name);

  boolean existsByName(String name);
}
