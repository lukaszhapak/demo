package com.example.hexagonal.job.movie;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.domain.movie.MovieCategory;
import com.example.hexagonal.domain.movie.MovieFacade;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class MovieGenerateJob {

  private final MovieFacade movieFacade;
  private final Random random = new Random();

  @Scheduled(cron = "${movie.generate.cron}")
  void generateMovie() {
	Movie movie = getMovie();
	log.trace("Generating Movie={}", movie);
	movieFacade.createMovie(movie);
  }

  private Movie getMovie() {
	Movie movie = new Movie();
	movie.setTitle("Title" + random.nextInt(10000));
	movie.setAuthor("Author" + random.nextInt(10000));
	movie.setCategory(MovieCategory.COMEDY);
	return movie;
  }
}
