package com.example.demo.modules.student.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentEndpoint {

  @GetMapping("/api/student/{id}")
  Student getStudentById(@PathVariable Long id);

  @PostMapping("/api/student")
  Student saveStudent(@RequestBody Student student);
}
