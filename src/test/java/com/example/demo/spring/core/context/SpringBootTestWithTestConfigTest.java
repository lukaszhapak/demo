package com.example.demo.spring.core.context;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestContextWithAutoConfiguration.class)
class SpringBootTestWithTestConfigTest {

  @Autowired
  StudentService studentService;

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student")
  void shouldSaveStudent() {
	// given
	Student student = new Student();

	// when
	List<Student> all = studentRepository.findAll();
	System.out.println(all.size());
	studentService.save(student);

	// then
	all = studentRepository.findAll();
	System.out.println(all.size());
  }
}
