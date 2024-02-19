package com.example.demo.nonspring.test;


class StudentRepository {

  public Student save(Student student) {
	return student;
  }

  public Student getStudentByName(String name) {
	return new Student(1L, name, 24);
  }


  public Student getStudentById(Long id) {
	return new Student(id, "John", 24);
  }

  public Student getStudentByNameAndId(String name, Long id) {
	return new Student(id, name, 24);
  }
}
