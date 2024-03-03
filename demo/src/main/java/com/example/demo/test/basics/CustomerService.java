package com.example.demo.test.basics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer save(Customer customer) {
	return customerRepository.save(customer);
  }

  public Customer getCustomerByName(String name) {
	return customerRepository.getCustomerByName(name);
  }

  public Customer getCustomerById(Long id) {
	return customerRepository.getCustomerById(id);
  }

  public Customer getCustomerByNameAndId(String name, Long id) {
	return customerRepository.getCustomerByNameAndId(name, id);
  }
}
