package com.example.demo.nonspring.mockito;


class StudentRepository {

  public Student save(Student student) {
	return student;
  }

  public Student getStudentByName(String name) {
	return new Student(1L, name, 24);
  }
}
