package com.example.demo.student;

interface StudentRepository{

  Student getStudentById(Long id);

  Student saveStudent(Student student);
}
