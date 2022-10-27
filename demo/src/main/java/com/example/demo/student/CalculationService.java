package com.example.demo.student;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CalculationService {

  private final StudentRepository studentRepository;

  @Scheduled(fixedRate = 1000)
  void calculateSomeShit() {
	Long count = studentRepository.count();
	System.out.println(count + ",  " + LocalDateTime.now());
  }
}
