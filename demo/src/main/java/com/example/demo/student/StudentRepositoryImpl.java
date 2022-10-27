package com.example.demo.student;

import org.springframework.stereotype.Repository;

@Repository
class StudentRepositoryImpl implements StudentRepository{

  @Override
  public Student getStudentById(Long id) {
	Student student = new Student();
	student.setId(id);
	student.setName("John");
	student.setAge(22);
	return student;
  }

  @Override
  public Student saveStudent(Student student) {
	return student;
  }

  @Override
  public Long count() {
	return 25L;
  }
}
