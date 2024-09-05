package com.example.demo.spring.modules.message.internalEvent;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class InternalEventListener {

  private final StudentService studentService;

  @EventListener
  void handleInternalEvent(InternalStudentUpdatedEvent event) {
	log.debug("Event received InternalEvent={}", event);
	Student student = studentService.findById(event.getId());
	student.setFirstName(event.getFirstName());
	studentService.save(student);
  }
}
