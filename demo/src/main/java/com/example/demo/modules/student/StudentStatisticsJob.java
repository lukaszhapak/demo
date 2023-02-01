package com.example.demo.modules.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
	value="job.student.statistics.enabled",
	havingValue = "true")
class StudentStatisticsJob {

  private final StudentService studentService;

  @Scheduled(cron = "${job.student.statistics.cron}")
  void calculateStatistics() {
	log.trace("Running student statistics job");
	Long studentsCount = studentService.calculateStatistics();
  }
}
