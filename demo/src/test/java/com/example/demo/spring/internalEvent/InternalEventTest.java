package com.example.demo.spring.internalEvent;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class InternalEventTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  InternalEventPublisher internalEventPublisher;

  @Test
  @DisplayName("should send event")
  void shouldSendEvent() {
	internalEventPublisher.send();
  }
}