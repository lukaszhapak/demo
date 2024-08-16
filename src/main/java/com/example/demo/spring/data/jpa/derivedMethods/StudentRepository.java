package com.example.demo.spring.data.jpa.derivedMethods;

import java.util.List;
import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<Student, Long> {

  // find {limit} By {property / properties expression} {comparison} {ordering operator}

  Student save(Student student);

  Student findById(Long id);

  <T> T findById(Long id, Class<T> type);

  <T> List<T> findByName(String name, Class<T> type);

  StudentDTO findByAge(int age);

  <T> T findByAge(int age, Class<T> type);

  List<Student> findByAddressStreetName(String streetName);

  <T> List<T> findByAddressStreetName(String streetName, Class<T> type);

  List<Student> findByAddressStreetNameAndAddressFlatNumber(String StreetNumber, String flatNumber);

  boolean existsByName(String name);

  Student findFirstByOrderByAgeAsc();

  List<Student> findFirst10ByOrderByAgeAsc();

  Long count();
}
