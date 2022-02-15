package com.example.demo;

import lombok.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Cacheable(value = "student", key = "'student' +#id")
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Cacheable(value = "students", key = "'students'")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Cacheable(value = "student", key = "'student' +#id")
    public Student update(Long id, Student student) {
        return studentRepository.save(student);
    }

    @CacheEvict(value = "students", key = "'students'")
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @CacheEvict(value = "student", key = "'student' +#id")
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
