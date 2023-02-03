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
	StudentEntity studentEntity = StudentEntity.of(getStudent());
	studentEntity.setId(1_000_001L);

	super.insertData(List.of(studentEntity));
  }

  public void insertStatisticsData() {
	StudentEntity studentEntity = StudentEntity.of(getStudent());
	studentEntity.setGrades("4,2,5,3,3");
	studentEntity.setId(1000001L);
	StudentEntity studentEntity2 = StudentEntity.of(getStudent());
	studentEntity2.setGrades("2,3,4,2");
	studentEntity2.setId(1000002L);
	StudentEntity studentEntity3 = StudentEntity.of(getStudent());
	studentEntity3.setGrades("4,3,5");
	studentEntity3.setId(1000003L);
	super.insertData(List.of(studentEntity, studentEntity2, studentEntity3));
  }

  public void cleanData() {
	super.cleanData();
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
	return list.stream()
		.map(student -> new StudentStatisticsDTO(student.getId(), student.getGrades()))
		.collect(Collectors.toList());
  }
}
