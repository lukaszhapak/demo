package com.example.demo.shared.test.context;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SecondService {

  private final MessageSender messageSender;
  private final NotNeededService notNeededService;

  public void operation() {
	messageSender.SendMessage("second service operation completed");
  }
}
