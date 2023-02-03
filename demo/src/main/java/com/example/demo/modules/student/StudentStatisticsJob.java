package com.example.demo.modules.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
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

  private final StudentStatisticsService studentStatisticsService;

  @Scheduled(cron = "${job.student.statistics.cron}")
  @SchedulerLock(name = "CalculateStatistics", lockAtLeastFor = "10S")
  void calculateStatistics() {
	log.debug("Running student statistics job");
	studentStatisticsService.calculateStatistics();
  }
}
