package com.example.demo.spring.jpql;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("Select s.name from Student s where s.id = :id")
  String selectNameById(Long id);

  @Query("SELECT NEW com.example.demo.spring.jpql.StudentDTO(s.name, s.age) FROM Student s")
  List<StudentDTO> findAllAsDTOs();
}
