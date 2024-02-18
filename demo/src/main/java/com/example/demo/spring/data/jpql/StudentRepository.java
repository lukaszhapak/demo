package com.example.demo.spring.data.jpql;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StudentRepository extends JpaRepository<Student, Long> {

  // find {limit} By {property / properties expression} {comparison} {ordering operator}

  @Query("SELECT NEW com.example.demo.spring.data.jpql.StudentDTO(s.name, s.age) FROM Student s")
  List<StudentDTO> findAllAsDTOs();

  @Query("SELECT NEW com.example.demo.spring.data.jpql.StudentDTO(s.name, s.age) FROM Student s where s.id = :id")
  StudentDTO findByIdAsDTOs(Long id);

  @Query("Select s.name from Student s where s.id = :id")
  String findNameById(Long id);

  @Query("Select s.name, s.age from Student s where s.id = :id")
  String findNameAndAgeById(Long id);

  @Query("Select s from Student s where s.address.streetName = :streetName")
  List<Student> findByAddressStreetName(String streetName);

  @Query("Select s from Student s where s.address.streetName = :streetName and s.address.flatNumber = :flatNumber")
  List<Student> findByAddressStreetNameAndFlatNumber(String streetName, String flatNumber);
}
