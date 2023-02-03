package com.example.demo.modules.student;

import static com.example.demo.commons.helper.TestUtils.getStudent;

import com.example.demo.commons.AbstractRepositoryInMemory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

class StudentRepositoryInMemory extends AbstractRepositoryInMemory<StudentEntity> implements
	StudentRepository {

  public StudentRepositoryInMemory() {
	cleanData();
	insertSampleData();
  }

  public void insertSampleData() {
	Student student = getStudent();
	map.put(1000001L, StudentEntity.of(student));
	map.put(1000002L, StudentEntity.of(student));
	map.put(1000003L, StudentEntity.of(student));
  }

  public void insertStatisticsData() {
	Student student = getStudent();
	student.setGrades(List.of(4,2,5,3,3));
	map.put(1000001L, StudentEntity.of(student));
	student.setGrades(List.of(2,3,4,2));
	map.put(1000002L, StudentEntity.of(student));
	student.setGrades(List.of(4,3,5));
	map.put(1000003L, StudentEntity.of(student));
  }

  public void cleanData() {
	map.clear();
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

  @Override
  public List<StudentStatisticsDTO> getStudentStatistics() {
	return map.values().stream()
		.map(student -> new StudentStatisticsDTO(student.getId(), student.getGrades()))
		.collect(Collectors.toList());
  }
}
