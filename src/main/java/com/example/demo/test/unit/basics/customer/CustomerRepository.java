package com.example.demo.test.unit.basics.customer;


public interface CustomerRepository {

  Customer save(Customer customer);

  Customer findByName(String name);

  Customer findById(Long id);

  Customer findByNameAndId(String name, Long id);
}
