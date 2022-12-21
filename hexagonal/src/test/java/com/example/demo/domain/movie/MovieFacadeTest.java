package com.example.demo.domain.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MovieFacadeTest {

  private final MovieCreatedEventPublisher movieCreatedEventPublisher = mock(
	  MovieCreatedEventPublisher.class);
  private final MovieStorage movieStorage = mock(MovieStorage.class);
  private final MovieValidator movieValidator = new MovieValidator();
  private final MovieFacade movieFacade = new MovieFacade(movieStorage, movieCreatedEventPublisher, movieValidator);

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

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"a", "more than 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should not create movie with invalid title")
  void shouldNotCreateMovieWithInvalidTitle(String title) {
	// given
	Movie movie = getMovie();
	movie.setTitle(title);

	// when
	Throwable thrown = catchThrowable(() -> movieFacade.createMovie(movie));

	// then
	assertThat(thrown).isInstanceOf(MovieValidationException.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"a", "more than 64 characters aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
  @DisplayName("should not create movie with invalid author")
  void shouldNotCreateMovieWithInvalidAuthor(String author) {
	// given
	Movie movie = getMovie();
	movie.setAuthor(author);

	// when
	Throwable thrown = catchThrowable(() -> movieFacade.createMovie(movie));

	// then
	assertThat(thrown).isInstanceOf(MovieValidationException.class);
  }

  private Movie getMovie() {
	Movie movie = new Movie();
	movie.setId(100L);
	movie.setTitle("smierc w wenecji");
	movie.setAuthor("andrzej");
	return movie;
  }
}