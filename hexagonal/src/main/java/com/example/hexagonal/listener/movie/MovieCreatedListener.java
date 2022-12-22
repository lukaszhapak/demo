package com.example.hexagonal.listener.movie;

import com.example.hexagonal.domain.movie.MovieCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
class MovieCreatedListener {

  @EventListener
  public void handleMovieCreated(MovieCreatedEvent event) {
	log.trace("Got MovieCreatedEvent={}", event);
  }
}
