package com.example.demo.message;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MessageService {

  private final MessagePublisher messagePublisher;

  void publishMessage(Message message) {
    messagePublisher.publishMessage(message);
  }
}
