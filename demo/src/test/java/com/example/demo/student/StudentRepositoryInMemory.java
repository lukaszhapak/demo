package com.example.demo.student;

import static com.example.demo.TestUtils.getStudent;

import com.example.demo.AbstractRepositoryInMemory;
import java.util.Optional;

class StudentRepositoryInMemory extends AbstractRepositoryInMemory<Student> implements StudentRepository{

  public StudentRepositoryInMemory() {
	map.put(10000L, getStudent());
  }

  @Override
  public Optional<Student> findStudentById(Long id) {
	return super.findById(id);
  }

  @Override
  public Student save(Student student) {
	return super.save(student);
  }

  @Override
  public Long count() {
	return super.count();
  }
}
