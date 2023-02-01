package com.example.demo.modules.student;

import static com.example.demo.commons.TestUtils.getStudent;

import com.example.demo.commons.AbstractRepositoryInMemory;
import java.util.Optional;

class StudentRepositoryInMemory extends AbstractRepositoryInMemory<StudentEntity> implements
	StudentRepository {

  public StudentRepositoryInMemory() {
	map.put(10000L, StudentEntity.of(getStudent()));
	map.put(10001L, StudentEntity.of(getStudent()));
	map.put(10002L, StudentEntity.of(getStudent()));
  }

  @Override
  public Optional<StudentEntity> findStudentById(Long id) {
	return super.findById(id);
  }

  @Override
  public StudentEntity save(StudentEntity studentEntity) {
	return super.save(studentEntity);
  }

  @Override
  public Long count() {
	return super.count();
  }

  @Override
  public Long deleteAllById(Long id) {
	return super.deleteAllById(id);
  }

  @Override
  public boolean existsById(Long id) {
	return super.existsById(id);
  }
}
