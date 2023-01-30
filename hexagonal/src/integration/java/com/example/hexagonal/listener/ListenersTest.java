package com.example.hexagonal.listener;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.hexagonal.AbstractIntegrationTest;
import com.example.hexagonal.domain.movie.MovieCreatedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

class ListenersTest extends AbstractIntegrationTest {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Test
  @DisplayName("should invoke movie created listener")
  void shouldInvokeMovieCreatedListener() {
	//given
	MovieCreatedEvent event = new MovieCreatedEvent();

	// when
	applicationEventPublisher.publishEvent(event);

	// then
	verify(movieCreatedListener, times(1)).handleMovieCreated(eq(event));
  }
}
