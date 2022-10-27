package com.example.demo.student;

public interface StudentRepository{

  Student getStudentById(Long id);

  Student saveStudent(Student student);
}
