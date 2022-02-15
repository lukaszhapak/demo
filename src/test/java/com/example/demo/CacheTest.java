package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.cache.*;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {
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
        studentService.findById(1L);
        studentService.update(1L, new Student());
        studentService.findById(1L);
        studentService.findById(1L);

        verify(studentRepository, times(1)).findById(any());
    }

    @Test
    void findAll() {
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        studentService.findAll();
        studentService.findAll();
        studentService.save(new Student());
        studentService.findAll();
        studentService.findAll();

        verify(studentRepository, times(2)).findAll();
    }

    @Test
    void delete() {
        when(studentRepository.findById(any())).thenReturn(Optional.of(new Student()));

        studentService.findById(1L);
        studentService.findById(1L);
        studentService.delete(1L);
        studentService.findById(1L);
        studentService.findById(1L);

        verify(studentRepository, times(2)).findById(any());
    }

    @AfterEach
    void tearDown() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
