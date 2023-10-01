package com.example.demo.spring.shared.test.context;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.demo.spring.shared.test.context.MessageSender;
import com.example.demo.spring.shared.test.context.NotNeededService;
import com.example.demo.spring.shared.test.context.SecondService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SecondServiceTest {

  @MockBean
  NotNeededService notNeededService;
  @MockBean
  MessageSender messageSender;
  @Autowired
  SecondService secondService;

  @Test
  @DisplayName("should complete operation and send message")
  void shouldCompleteOperationAndSendMessage() {
	// given

	// when
	secondService.operation();

	// then
	verify(messageSender, Mockito.times(1)).SendMessage(any());
  }

}