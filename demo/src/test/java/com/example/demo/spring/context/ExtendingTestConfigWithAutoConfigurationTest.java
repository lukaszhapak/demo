package com.example.demo.spring.context;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
class ExtendingTestConfigWithAutoConfigurationTest {

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
