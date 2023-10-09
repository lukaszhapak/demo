package com.example.demo.spring.cache;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceTest {

  @Autowired
  StudentService studentService;
  @MockBean
  StudentRepository studentRepository;
  @Autowired
  CacheManager cacheManager;

  @Test
  void findOne() {
	when(studentRepository.findById(any())).thenReturn(Optional.of(new Student()));

	studentService.findById(1L);
	studentService.save(new Student());
	studentService.findById(1L);
	studentService.update(1L, new Student());
	studentService.findById(1L);
	studentService.update(2L, new Student());
	studentService.findById(1L);
	studentService.deleteById(1L);
	studentService.findById(1L);
	studentService.deleteById(2L);
	studentService.findById(1L);

	verify(studentRepository, times(3)).findById(any());
  }

  @Test
  void findAll() {
	when(studentRepository.findAll()).thenReturn(new ArrayList<>());

	studentService.findAll();
	studentService.findAll();
	studentService.save(new Student());
	studentService.findAll();
	studentService.findAll();
	studentService.update(1L, new Student());
	studentService.findAll();
	studentService.findAll();
	studentService.deleteById(1L);
	studentService.findAll();
	studentService.findAll();

	verify(studentRepository, times(4)).findAll();
  }

  @AfterEach
  void tearDown() {
	cacheManager.getCacheNames()
		.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
  }
}
