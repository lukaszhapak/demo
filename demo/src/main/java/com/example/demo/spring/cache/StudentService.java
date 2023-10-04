package com.example.demo.spring.cache;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  @Cacheable(value = "student", key = "'student' +#id")
  public Student findById(Long id) {
	log.debug("get student id={}", id);
	return studentRepository.findById(id).orElseThrow();
  }

  @Cacheable(value = "student", key = "'students'")
  public List<Student> findAll() {
	log.debug("get students");
	return studentRepository.findAll();
  }

  @CacheEvict(value = "student", key = "'students'")
  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Caching(evict = {
	  @CacheEvict(value = "student", key = "'students'"),
	  @CacheEvict(value = "student", key = "'student' +#id")
  })
  public Student update(Long id, Student student) {
	log.debug("update student id={}, student={}", id, student);
	student.setId(id);
	return studentRepository.save(student);
  }

  @Caching(evict = {
	  @CacheEvict(value = "student", key = "'students'"),
	  @CacheEvict(value = "student", key = "'student' +#id")
  })
  public void deleteById(Long id) {
	log.debug("delete student id={}", id);
	studentRepository.deleteById(id);
  }
}
