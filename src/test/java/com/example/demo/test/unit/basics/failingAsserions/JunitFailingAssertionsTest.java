package com.example.demo.test.unit.basics.failingAsserions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.test.unit.basics.customer.CustomerDTO;
import java.util.List;
import org.junit.jupiter.api.Test;

class JunitFailingAssertionsTest {

  CustomerDTO customerDTO = new CustomerDTO("John", 24);

  List<Integer> list = List.of(1, 2, 3, 4);
//
//  @Test
//  void shouldAssertListSizeJunit() {
//	assertEquals(2, list.size());
//  }
//
//  @Test
//  void shouldAssertBooleanValueJunit() {
//	assertTrue(customerDTO.isBooleanValue());
//  }
//
//  @Test
//  void shouldAssertNameJunit() {
//	assertEquals("Michael", customerDTO.getName());
//  }
//
//  @Test
//  void shouldAssertStreamJunit() {
//	assertTrue(list.stream().allMatch(number -> number > 3));
//  }
}