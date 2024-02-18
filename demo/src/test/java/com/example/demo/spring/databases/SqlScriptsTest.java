package com.example.demo.spring.databases;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:sql/insert-student.sql")
class SqlScriptsTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should execute sql file and get students")
  void shouldExecuteSqlFileAndGetStudents() {
	// given

	// when
	List<Student> students = studentRepository.findAll();

	// then
	assertThat(students.size()).isEqualTo(2);
  }

  @Test
  @Sql("classpath:sql/insert-additional-student.sql")
  @DisplayName("should execute sql files and get students")
  void shouldExecuteSqlFilesAndGetStudents() {
	// given

	// when
	List<Student> students = studentRepository.findAll();

	// then
	assertThat(students.size()).isEqualTo(3);
  }
}