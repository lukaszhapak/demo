package com.example.demo.modules.student;

import com.example.demo.modules.student.api.Student;
import com.example.demo.modules.student.api.StudentEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class StudentController implements StudentEndpoint {

  private final StudentService studentService;

  @Override
  public Student getStudentById(@PathVariable Long id) {
	return studentService.getStudentById(id);
  }

  @Override
  public Student saveStudent(@RequestBody Student student) {
	return studentService.saveStudent(student);
  }
}
