package com.example.demo.spring.modules.message.internalEvent;

import com.example.demo.spring.base.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class InternalEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  void publishStudentRegisteredEvent(Student student) {
	InternalStudentRegisteredEvent event = new InternalStudentRegisteredEvent().setBody(student.getFirstName());
	log.debug("Publishing event={}", event);
	applicationEventPublisher.publishEvent(event);
  }
}
