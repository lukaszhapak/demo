package com.example.demo.domain.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieFacadeTest {

  private final MovieCreatedEventSender movieCreatedEventSender = mock(
	  MovieCreatedEventSender.class);
  private final MovieStorage movieStorage = mock(MovieStorage.class);
  private final MovieFacade movieFacade = new MovieFacade(movieStorage, movieCreatedEventSender);

  @Test
  @DisplayName("should find movie by id")
  void shouldFindMovieById() {
	// given
	long id = 100L;
	when(movieStorage.findById(id)).thenReturn(Optional.of(getMovie()));

	// when
	Movie movie = movieFacade.findById(id);

	// then
	assertThat(movie).usingRecursiveComparison().isEqualTo(getMovie());
  }

  @Test
  @DisplayName("should throw exception when movie with given id doesnt exist")
  void shouldThrowExceptionWhenMovieWithGivenIdDoesntExist() {
	// given
	long notExistingMovieId = 100L;
	when(movieStorage.findById(notExistingMovieId)).thenReturn(Optional.empty());

	// when
	Throwable thrown = catchThrowable(() -> movieFacade.findById(notExistingMovieId));

	// then
	assertThat(thrown).isInstanceOf(MovieNotFoundException.class);
  }

  @Test
  @DisplayName("should create movie")
  void shouldCreateMovie() {
	// given
	when(movieStorage.create(any())).thenReturn(getMovie());
	Movie movie = getMovie();

	// when
	Movie movieResponse = movieFacade.createMovie(movie);

	// then
	assertThat(movie).usingRecursiveComparison().isEqualTo(movieResponse);
  }

  private Movie getMovie() {
	Movie movie = new Movie();
	movie.setId(100L);
	movie.setTitle("smierc w wenecji");
	movie.setAuthor("andrzej");
	return movie;
  }
}