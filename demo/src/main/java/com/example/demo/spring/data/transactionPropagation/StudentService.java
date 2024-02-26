package com.example.demo.spring.data.transactionPropagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  @Transactional
  public Student findById(Long id) {
	log.debug("get student id={}", id);
	return studentRepository.findById(id).orElseThrow();
  }

  @Transactional
//  @Transactional(propagation = Propagation.REQUIRED) // it is default one
  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public Student saveMandatory(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NESTED)
  public Student saveNested(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NEVER)
  public Student saveNever(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public Student saveNotSupported(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Student saveRequiresNew(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }
}