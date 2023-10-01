package com.example.demo.spring.shared.test.context;

import org.springframework.stereotype.Component;

@Component
class MessageSender {

  public void SendMessage(String message) {
    System.out.println("sending message: " + message);
  }
}
