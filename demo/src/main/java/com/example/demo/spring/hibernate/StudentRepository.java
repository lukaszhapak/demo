package com.example.demo.spring.hibernate;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("Select s.firstName from Student s where s.id = :id")
  String findFirstNameById(Long id);

  @Query("Select s from Student s where s.address.streetName = :streetName")
  Student findByAddressStreetName(String streetName);

  @Query("SELECT NEW com.example.demo.spring.hibernate.StudentDTO(s.firstName, s.age) FROM Student s")
  List<StudentDTO> findAllAsDTOs();
}
