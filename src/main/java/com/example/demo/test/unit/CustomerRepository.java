package com.example.demo.test.unit;


public interface CustomerRepository {

  Customer save(Customer customer);

  Customer getCustomerByName(String name);

  Customer getCustomerById(Long id);

  Customer getCustomerByNameAndId(String name, Long id);
}
