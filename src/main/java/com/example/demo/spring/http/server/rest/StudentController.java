package com.example.demo.spring.http.server.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
class StudentController {

  private final StudentService studentService;

  @GetMapping("/api/param/single")
  public ResponseDTO singleParam(@RequestParam(required = true) String name) {
	return new ResponseDTO().setSingleParam(name);
  }

  @GetMapping("/api/header/single")
  public ResponseDTO singleHeader(@RequestHeader(required = true) String name) {
	return new ResponseDTO().setSingleHeader(name);
  }

  @PostMapping("/api/all")
  public ResponseDTO all(ParamsDTO paramsDTO, @RequestHeader("user-id") String userId, @RequestBody Student student) {
	return new ResponseDTO().setParams(paramsDTO).setSingleHeader(userId).setBody(student);
  }

  @GetMapping("/api/student/{id}")
  public Student findById(@PathVariable Long id) {
	return studentService.findById(id);
  }

  @GetMapping("/api/student")
  public List<Student> findAll() {
	return studentService.findAll();
  }

  @PutMapping("/api/student/{id}")
  public Student update(@PathVariable Long id, @RequestBody Student student) {
	return studentService.update(id, student);
  }

  @PostMapping("/api/student")
  public Student save(@RequestBody Student student) {
	return studentService.save(student);
  }

  @DeleteMapping("/api/student/{id}")
  public void delete(@PathVariable Long id) {
	studentService.deleteById(id);
  }
}
