package com.example.demo.spring.modules.data.jpa.repository.queryMethods;

import com.example.demo.spring.base.dto.StudentDTO;
import com.example.demo.spring.base.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface QueryMethodsStudentRepository extends JpaRepository<Student, Long> {

  // when class dto have fields with same names and single constructor this is not needed
  @Query("SELECT NEW com.example.demo.spring.base.dto.StudentDTO(s.firstName, s.age) FROM Student s")
  List<StudentDTO> findAllAsDTOs();

  @Query("SELECT NEW com.example.demo.spring.base.dto.StudentDTO(s.firstName, s.age) FROM Student s where s.id = :id")
  StudentDTO findByIdAsDTOs(Long id);

  @Query("Select s.firstName from Student s where s.id = :id")
  String findFirstNameById(Long id);

  @Query("Select s.firstName, s.age from Student s where s.id = :id")
  String findFirstNameAndAgeById(Long id);

  @Query("Select s from Student s where s.address.streetName = :streetName")
  List<Student> findByAddressStreetName(String streetName);

  @Query("Select s from Student s where s.address.streetName = :streetName and s.address.flatNumber = :flatNumber")
  List<Student> findByAddressStreetNameAndFlatNumber(String streetName, String flatNumber);
}
