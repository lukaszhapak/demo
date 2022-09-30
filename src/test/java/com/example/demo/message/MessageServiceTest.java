package com.example.demo.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class MessageServiceTest {

  private final ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
  private final MessagePublisher messagePublisher = mock(MessagePublisher.class);
  private final MessageService messageService = new MessageService(messagePublisher);

  @Test
  @DisplayName("should publish message")
  void shouldPublishMessage() {
	// given
	Message message = getMessage();

	// when
	messageService.publishMessage(message);

	// then
	verify(messagePublisher, times(1)).publishMessage(messageCaptor.capture());

	assertThat(messageCaptor.getValue().getAuthor()).isEqualTo(getMessage().getAuthor());
	assertThat(messageCaptor.getValue().getTitle()).isEqualTo(getMessage().getTitle());
	assertThat(messageCaptor.getValue().getBody()).isEqualTo(getMessage().getBody());
  }

  private Message getMessage() {
	Message message = new Message();
	message.setAuthor("John");
	message.setTitle("Hello my friend");
	message.setBody("Hello Roman wanna go bowling?");
	return message;
  }
}