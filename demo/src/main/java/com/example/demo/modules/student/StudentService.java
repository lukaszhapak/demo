package com.example.demo.modules.student;

public interface StudentService {

  Student getStudentById(Long id);

  Student saveStudent(Student student);
}
