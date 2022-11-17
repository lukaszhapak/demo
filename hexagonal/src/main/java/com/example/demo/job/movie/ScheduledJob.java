package com.example.demo.job.movie;

import com.example.demo.domain.movie.MovieFacade;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ScheduledJob {

  private final MovieFacade movieFacade;

  @Scheduled(fixedRate = 1000L)
  void run() {
    System.out.println(LocalDateTime.now());
  }
}
