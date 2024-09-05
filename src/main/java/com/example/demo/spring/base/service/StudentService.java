package com.example.demo.spring.base.service;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> findAll() {
	log.debug("getting all students");
	return studentRepository.findAll();
  }

  public Student findById(long id) {
	log.debug("getting student by id={}", id);
	return studentRepository.findById(id).orElse(null);
  }

  public Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }

  public void deleteById(long id) {
	log.debug("deleting student by id={}", id);
	studentRepository.deleteById(id);
  }
}

