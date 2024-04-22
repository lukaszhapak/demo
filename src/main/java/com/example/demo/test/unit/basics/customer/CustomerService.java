package com.example.demo.test.unit.basics.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer save(Customer customer) {
	return customerRepository.save(customer);
  }

  public Customer findByName(String name) {
	return customerRepository.findByName(name);
  }

  public Customer findById(Long id) {
	return customerRepository.findById(id);
  }

  public Customer findByNameAndId(String name, Long id) {
	return customerRepository.findByNameAndId(name, id);
  }
}
