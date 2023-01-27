package com.example.hexagonal.job.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class MovieGenerateJob {

  private final MovieGenerateService movieGenerateService;

  @Scheduled(cron = "${movie.generate.cron}")
  void generateMovie() {
	log.trace("Generating Movie");
	movieGenerateService.generateMovie();
  }
}
