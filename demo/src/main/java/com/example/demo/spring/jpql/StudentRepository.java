package com.example.demo.spring.jpql;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("SELECT NEW com.example.demo.spring.jpql.StudentDTO(s.name, s.age) FROM Student s")
  List<StudentDTO> findAllAsDTOs();

  @Query("Select s.name from Student s where s.id = :id")
  String findNameById(Long id);

  @Query("Select s from Student s where s.address.streetName = :streetName")
  List<Student> findByAddressStreetName(String streetName);

  @Query("Select s from Student s where s.address.streetName = :streetName and s.address.flatNumber = :flatNumber")
  List<Student> findByAddressStreetNameAndFlatNumber(String streetName, String flatNumber);
}
