package com.example.hexagonal.job.movie;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.domain.movie.MovieCategory;
import com.example.hexagonal.domain.movie.MovieFacade;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MovieGenerateService {

  private final MovieFacade movieFacade;
  private final Random random = new Random();

  void generateMovie() {
	Movie movie = getMovie();
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
