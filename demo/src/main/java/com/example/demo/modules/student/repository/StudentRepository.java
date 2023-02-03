package com.example.demo.modules.student.repository;

import com.example.demo.modules.student.statistics.StudentStatisticsDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<StudentEntity, Long> {

  Optional<StudentEntity> findStudentById(Long id);

  StudentEntity save(StudentEntity studentEntity);

  Long deleteAllById(Long id);

  boolean existsById(Long id);

  Page<StudentEntity> findAll(Specification<StudentEntity> specification, Pageable pageable);

  @Query("SELECT NEW com.example.demo.modules.student.statistics.StudentStatisticsDTO(s.id, s.grades) FROM StudentEntity s")
  List<StudentStatisticsDTO> getStudentStatistics();
}
