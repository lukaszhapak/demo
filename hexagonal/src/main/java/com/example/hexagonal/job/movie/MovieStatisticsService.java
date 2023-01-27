package com.example.hexagonal.job.movie;


import com.example.hexagonal.domain.movie.MovieFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MovieStatisticsService {

  private final MovieFacade movieFacade;

  public Long count() {
	return movieFacade.count();
  }
}
