package com.example.demo.spring.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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

  @GetMapping("/cache/student/{id}")
  public Student findById(@PathVariable Long id) {
	return studentService.findById(id);
  }

  @GetMapping("/cache/student")
  public List<Student> findAll() {
	return studentService.findAll();
  }

  @PutMapping("/cache/student/{id}")
  public Student update(@PathVariable Long id, @RequestBody Student student) {
	return studentService.update(id, student);
  }

  @PostMapping("/cache/student")
  public Student save(@RequestBody Student student) {
	return studentService.save(student);
  }

  @DeleteMapping("/cache/student/{id}")
  public void delete(@PathVariable Long id) {
	studentService.deleteById(id);
  }
}
