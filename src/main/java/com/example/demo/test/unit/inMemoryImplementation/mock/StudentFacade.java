package com.example.demo.test.unit.inMemoryImplementation.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class StudentFacade {

  private final StudentRepository studentRepository;
  private final StudentEventPublisher studentEventPublisher;
  private final StudentValidator studentValidator;
  private final StudentMapper studentMapper;

  StudentDTO save(StudentDTO studentDTO) {
	log.debug("saving studentDTO={}", studentDTO);
	Student student = studentMapper.toDomain(studentDTO);
	studentValidator.validate(student);
	studentEventPublisher.publishStudentSavedEvent(student);
	studentRepository.save(student);
	return studentMapper.toDTO(student);
  }

  StudentDTO findById(Long id) {
	log.debug("getting by id={}", id);
	return studentMapper.toDTO(studentRepository.findById(id));
  }

  StudentDTO findByName(String name) {
	log.debug("getting by name={}", name);
	return studentMapper.toDTO(studentRepository.findByName(name));
  }
}
