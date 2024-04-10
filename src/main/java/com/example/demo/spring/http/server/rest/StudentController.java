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

  @GetMapping("/api/string")
  public String string() {
	return "Student";
  }

  @GetMapping("/api/param/single")
  public ResponseDTO singleParam(@RequestParam String singleParam) {
	return new ResponseDTO().setSingleParam(singleParam);
  }

  @GetMapping("/api/param/multi")
  public ResponseDTO multipleParams(ParamsDTO paramsDTO) {
	return new ResponseDTO().setParams(paramsDTO);
  }

  @GetMapping("/api/header/multi")
  public ResponseDTO multipleHeaders(@RequestHeader String name, @RequestHeader int age, @RequestHeader List<Integer> ids) {
	return new ResponseDTO().setHeaders(new HeadersDTO().setName(name).setAge(age).setIds(ids));
  }

  @GetMapping("/api/all/{pathVariable}")
  public ResponseDTO all(ParamsDTO paramsDTO, @RequestHeader("singleHeader") String singleHeader, @RequestBody Student student, @PathVariable String pathVariable) {
	return new ResponseDTO().setParams(paramsDTO).setSingleHeader(singleHeader).setBody(student).setPathVariable(pathVariable);
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
