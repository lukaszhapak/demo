package com.example.demo.spring.core.beanProcessor;

import org.springframework.stereotype.Service;

@Service
class StudentServiceImpl implements LoggerService, StudentService {

  @Override
  public void addStudent(String name, Integer age) {

  }
}
