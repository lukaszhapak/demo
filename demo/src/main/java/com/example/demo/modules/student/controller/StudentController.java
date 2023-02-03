package com.example.demo.modules.student.controller;

import com.example.demo.modules.student.domain.Student;
import com.example.demo.modules.student.domain.StudentSearchSpecification;
import com.example.demo.modules.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/api/student/{id}")
  public Student getStudentById(@PathVariable Long id) {
	return studentService.getStudentById(id);
  }

  @PostMapping("/api/student")
  public Student saveStudent(@RequestBody Student student) {
	return studentService.saveStudent(student);
  }

  @PutMapping("/api/student/{id}")
  public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
	return studentService.updateStudent(id, student);
  }

  @DeleteMapping("/api/student/{id}")
  public void deleteStudent(@PathVariable Long id) {
	studentService.deleteStudentById(id);
  }

  @GetMapping("/api/student")
  public Page<Student> findAll(@RequestParam(defaultValue = "1") Integer page,
	  @RequestParam(defaultValue = "20") Integer size,
	  @RequestParam(required = false) String sortBy,
	  @RequestParam(required = false) Boolean sortAscending,
	  @RequestParam(required = false) Integer id,
	  @RequestParam(required = false) Integer minAge,
	  @RequestParam(required = false) Integer maxAge,
	  @RequestParam(required = false) String name) {
	return studentService.findAll(new StudentSearchSpecification(page - 1, size, sortBy, sortAscending, id, minAge, maxAge, name));
  }
}
