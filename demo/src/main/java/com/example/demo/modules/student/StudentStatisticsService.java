package com.example.demo.modules.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
class StudentStatisticsService {

  private final StudentRepository studentRepository;

  public Long calculateStatistics() {
    log.trace("Get students count");
    return studentRepository.count();
  }
}
