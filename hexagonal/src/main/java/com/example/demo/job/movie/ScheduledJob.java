package com.example.demo.job.movie;

import com.example.demo.domain.movie.MovieFacade;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class ScheduledJob {

  private final MovieFacade movieFacade;

  @Scheduled(fixedRate = 1000000L)
  void run() {
	log.debug("running scheduled job timestamp={}", LocalDateTime.now());
  }
}
