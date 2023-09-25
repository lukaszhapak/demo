package com.example.demo.internalEvent;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class InternalEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Scheduled(fixedDelay = 10000)
  public void send() {
	InternalEvent event = new InternalEvent();
	event.setBody(UUID.randomUUID().toString());
	log.debug("Publishing InternalEvent={}", event);
	applicationEventPublisher.publishEvent(event);
  }

}
