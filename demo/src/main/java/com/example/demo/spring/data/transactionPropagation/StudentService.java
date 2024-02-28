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

//  @Transactional
  @Transactional(propagation = Propagation.REQUIRED) // it is default one/ supports transaction and creates new if none exists
  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.MANDATORY) // supports transaction and throws exception if none exists
  public Student saveMandatory(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NESTED) // creates nested transaction if transaction exists or creates new if none exists
  public Student saveNested(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NEVER) // executes with no transaction, if transaction already exists it throws exception
  public Student saveNever(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED) // executes with no transaction if one already exists it gets suspended
  public Student saveNotSupported(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW) // creates new transaction and suspends if it already exists
  public Student saveRequiresNew(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }
}