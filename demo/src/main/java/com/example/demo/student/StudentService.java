package com.example.demo.student;

public interface StudentService {

  Student getStudentById(Long id);

  Student saveStudent(Student student);
}
