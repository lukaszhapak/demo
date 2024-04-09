package com.example.demo.test.unit;

public class CustomerValidator {

  public boolean validate(Customer customer) {
	return customer.getName().length() > 2
		&& customer.getName().length() < 64;
  }
}
