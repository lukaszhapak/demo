package com.example.demo.spring.message.internalEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class InternalEventListener {

  private final StudentRepository studentRepository;

  @EventListener
  void handleEvent(InternalEvent event) {
	log.debug("Got InternalEvent={}", event);
	Student student = new Student();
	student.setName(event.getBody());
	studentRepository.save(student);
  }
}
