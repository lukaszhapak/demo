package com.example.demo.shared.test.context;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class FirstServiceTest {

  @MockBean
  MessageSender messageSender;
  @SpyBean
  NotNeededService notNeededService;
  @Autowired
  FirstService firstService;

  @Test
  @DisplayName("should complete operation and send message")
  void shouldCompleteOperationAndSendMessage() {
	// given

	// when
	firstService.operation();

	// then
	verify(messageSender, Mockito.times(1)).SendMessage(any());
  }

}