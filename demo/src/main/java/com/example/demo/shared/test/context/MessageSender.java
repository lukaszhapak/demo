package com.example.demo.shared.test.context;

import org.springframework.stereotype.Component;

@Component
public class MessageSender {

  public void SendMessage(String message) {
    System.out.println("sending message: " + message);
  }
}
