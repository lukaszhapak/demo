package com.example.demo.spring.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class StudentController {

  private final StudentService studentService;

  @PostMapping("/outbox/student")
  Student save(@RequestBody Student student) {
	return studentService.save(student);
  }
}
