package com.example.demo.modules.student;

import static com.example.demo.commons.helper.TestUtils.getStudent;

import com.example.demo.commons.AbstractRepositoryInMemory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

class StudentRepositoryInMemory extends AbstractRepositoryInMemory<StudentEntity> implements
	StudentRepository {

  public StudentRepositoryInMemory() {
	map.put(1000001L, StudentEntity.of(getStudent()));
	map.put(1000002L, StudentEntity.of(getStudent()));
	map.put(1000003L, StudentEntity.of(getStudent()));
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

  @Override
  public Page<StudentEntity> findAll(Specification<StudentEntity> specification,
	  Pageable pageable) {
	return null;
  }
}
