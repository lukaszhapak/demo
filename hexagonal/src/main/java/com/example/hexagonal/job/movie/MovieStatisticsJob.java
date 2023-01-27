package com.example.hexagonal.job.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class MovieStatisticsJob {

  private final MovieStatisticsService movieStatisticsService;

  @Scheduled(cron = "${movie.statistics.cron}")
  void calculateStatistics() {
	log.trace("Running movie statistics job, there are {} movies", movieStatisticsService.count());
  }
}
