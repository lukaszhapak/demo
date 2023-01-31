package com.example.demo.student;

import static com.example.demo.student.StudentTestUtils.getStudent;

import com.example.demo.AbstractRepositoryInMemory;

class StudentRepositoryInMemory extends AbstractRepositoryInMemory<Student> implements StudentRepository{

  public StudentRepositoryInMemory() {
	map.put(10000L, getStudent());
  }

  @Override
  public Student findStudentById(Long id) {
	return findById(id);
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
