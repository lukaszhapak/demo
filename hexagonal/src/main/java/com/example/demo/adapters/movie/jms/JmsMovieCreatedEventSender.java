package com.example.demo.adapters.movie.jms;

import com.example.demo.domain.movie.MovieCreatedEvent;
import com.example.demo.domain.movie.MovieCreatedEventSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
class JmsMovieCreatedEventSender implements MovieCreatedEventSender {

  @Override
  public void send(MovieCreatedEvent event) {
    log.debug("Sending MovieCreatedEvent={}", event);
  }
}
