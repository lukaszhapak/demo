package com.example.demo.job.movie;

import com.example.demo.domain.movie.MovieFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class MovieStatisticsJob {

  private final MovieFacade movieFacade;

  @Scheduled(cron = "${movie.statistics.cron}")
  void calculateStatistics() {
	Long count = movieFacade.count();
	log.debug("Running movie statistics job, there are {} movies", count);
  }
}
