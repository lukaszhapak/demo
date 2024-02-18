package com.example.demo.spring.tools.outbox;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class OutboxJob {

  private final OutboxService outboxService;
  private final OutboxPublisher outboxPublisher;

  @Scheduled(cron = "${outbox.cron}")
    void sendMessages(){
	log.debug("Sending messages");
	List<Outbox> unSentMessages = outboxService.getUnsentMessages();
	unSentMessages.forEach(this::sendMessage);
  }

  private void sendMessage(Outbox message){
	log.debug("Sending message={}", message);
	try {
	  outboxPublisher.publish(message);
	  outboxService.markMessageAsSent(message);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

}
