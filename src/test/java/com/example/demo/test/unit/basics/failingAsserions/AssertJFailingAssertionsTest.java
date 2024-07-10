package com.example.demo.test.unit.basics.failingAsserions;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.unit.basics.customer.CustomerDTO;
import java.util.List;
import org.junit.jupiter.api.Test;

class AssertJFailingAssertionsTest {

  CustomerDTO customerDTO = new CustomerDTO("John", 24);

  List<Integer> list = List.of(1, 2, 3, 4);
//
//  @Test
//  void shouldAssertListSizeAssertJ() {
//	assertThat(list).hasSize(2);
//  }
//
//  @Test
//  void shouldAssertBooleanValueAssertJ() {
//	assertThat(customerDTO.isBooleanValue()).isTrue();
//  }
//
//  @Test
//  void shouldAssertNameAssertJ() {
//	assertThat(customerDTO.getName()).isEqualTo("Michael");
//  }
//
//  @Test
//  void shouldAssertStreamJunit() {
//	assertThat(list.stream()).allMatch(number -> number > 3);
////	assertThat(list.stream()).allMatch(number -> number > 3, "all numbers should be > 3");
//  }
}