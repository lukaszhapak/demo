package com.example.hexagonal.job.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
	value="job.movie.generate.enabled",
	havingValue = "true")
class MovieGenerateJob {

  private final MovieGenerateService movieGenerateService;

  @Scheduled(cron = "${job.movie.generate.cron}")
  void generateMovie() {
	log.trace("Generating Movie");
	movieGenerateService.generateMovie();
  }
}
