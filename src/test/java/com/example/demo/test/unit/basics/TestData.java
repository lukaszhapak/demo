package com.example.demo.test.unit.basics;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerDTO;

public interface TestData {

  static Customer getCustomer() {
	return Customer.builder()
		.id(14L)
		.name("John")
		.age(22)
		.build();
  }

  static CustomerDTO getCustomerDTO() {
	return CustomerDTO.builder()
		.name("John")
		.age(22)
		.booleanValue(false)
		.build();
  }
}
