package com.example.demo.domain.movie;

public interface MovieCreatedEventPublisher {

  void send(MovieCreatedEvent event);
}
