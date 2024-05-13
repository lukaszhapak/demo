package com.example.demo.test.unit.inMemoryImplementation.mock;

interface StudentRepository {

  Student save(Student student);

  Student findById(Long id);

  Student findByName(String name);
}
