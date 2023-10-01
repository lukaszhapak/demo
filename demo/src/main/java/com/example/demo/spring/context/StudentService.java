package com.example.demo.spring.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Student save(Student student){
    log.debug("Saving student={}", student);
    return studentRepository.save(student);
  }
}
