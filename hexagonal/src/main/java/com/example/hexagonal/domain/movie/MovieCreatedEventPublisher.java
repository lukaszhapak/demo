package com.example.hexagonal.domain.movie;

public interface MovieCreatedEventPublisher {

  void send(MovieCreatedEvent event);
}
