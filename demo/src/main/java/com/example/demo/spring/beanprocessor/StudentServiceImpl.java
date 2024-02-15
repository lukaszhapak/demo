package com.example.demo.spring.beanprocessor;

import org.springframework.stereotype.Service;

@Service
class StudentServiceImpl implements LoggerService, StudentService {

  @Override
  public void addStudent(String name, Integer age) {

  }
}
