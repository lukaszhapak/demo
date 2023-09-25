package com.example.demo.internalEvent;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
class InternalEventListener {
  @EventListener
  public void handleMovieCreated(InternalEvent event) {
	log.debug("Got InternalEvent={}", event);
  }
}
