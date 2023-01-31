package com.example.demo.student;

import static com.example.demo.student.StudentTestUtils.getStudent;

import java.util.HashMap;

class StudentRepositoryInMemory implements StudentRepository {

  private final HashMap<Long, Student> students = new HashMap<>();
  private Long id = 1L;

  public StudentRepositoryInMemory() {
	students.put(10000L, getStudent());
  }

  @Override
  public Student findStudentById(Long id) {
	return students.get(id);
  }

  @Override
  public Student save(Student student) {
	setId(student);
	students.put(student.getId(), student);
	return student;
  }

  @Override
  public Long count() {
	return (long) students.size();
  }

  private void setId(Student student) {
	student.setId(id++);
  }
}
