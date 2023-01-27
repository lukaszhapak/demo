package com.example.hexagonal.job.movie;


import com.example.hexagonal.domain.movie.MovieFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MovieStatisticsService {

  private final MovieFacade movieFacade;

  public Long count() {
	return movieFacade.count();
  }
}
