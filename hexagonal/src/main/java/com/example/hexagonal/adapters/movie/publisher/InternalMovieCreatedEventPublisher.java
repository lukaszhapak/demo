package com.example.hexagonal.adapters.movie.publisher;

import com.example.hexagonal.domain.movie.MovieCreatedEvent;
import com.example.hexagonal.domain.movie.MovieCreatedEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@RequiredArgsConstructor
class InternalMovieCreatedEventPublisher implements MovieCreatedEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void send(MovieCreatedEvent event) {
	log.trace("Publishing MovieCreatedEvent={}", event);
	applicationEventPublisher.publishEvent(event);
  }
}
