package com.example.demo.spring.message.internalEvent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

class InternalEventListenerTest extends AbstractIntegrationTest {

  @Autowired
  ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should receive event")
  void shouldReceiveEvent() {
	// given
    InternalEvent event = new InternalEvent();
    event.setBody("John");

    // when
    applicationEventPublisher.publishEvent(event);

    // then
    assertThat(studentRepository.count()).isEqualTo(1);
  }
}