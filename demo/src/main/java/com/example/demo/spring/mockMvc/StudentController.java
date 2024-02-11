package com.example.demo.spring.mockMvc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
class StudentController {

  private final StudentService studentService;

  @GetMapping("/mock-mvc/student/{id}")
  public Student findById(@PathVariable Long id) {
	return studentService.findById(id);
  }

  @GetMapping("/mock-mvc/student")
  public List<Student> findAll() {
	return studentService.findAll();
  }

  @PutMapping("/mock-mvc/student/{id}")
  public Student update(@PathVariable Long id, @RequestBody Student student) {
	return studentService.update(id, student);
  }

  @PostMapping("/mock-mvc/student")
  public Student save(@RequestBody Student student) {
	return studentService.save(student);
  }

  @DeleteMapping("/mock-mvc/student/{id}")
  public void delete(@PathVariable Long id) {
	studentService.deleteById(id);
  }
}
