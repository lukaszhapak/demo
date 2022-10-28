package com.example.demo.domain.movie;

public interface MovieCreatedEventSender {

  void send(MovieCreatedEvent event);
}
