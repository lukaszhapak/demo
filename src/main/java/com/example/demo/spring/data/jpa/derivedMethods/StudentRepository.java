package com.example.demo.spring.data.jpa.derivedMethods;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {

  // find {limit} By {property / properties expression} {comparison} {ordering operator}

  <T> T findById(Long id, Class<T> type);

  StudentDTO findByAge(int age);

  List<Student> findByAddressStreetName(String streetName);

  List<Student> findByAddressStreetNameAndAddressFlatNumber(String StreetNumber, String flatNumber);

}
