package com.example.demo.test.basics;

public interface SampleCustomers {

  // by name
  CustomerDTO john = new CustomerDTO("John", 22);
  CustomerDTO Jim = new CustomerDTO("Jim", 26);
  CustomerDTO Michael = new CustomerDTO("Michael", 32);

  // or by some other parameter
  CustomerDTO customer1 = new CustomerDTO("John", 22);
  CustomerDTO customerWithRegisteredProduct = new CustomerDTO("John", 22);
  CustomerDTO customerWithAlreadyPaidOrder = new CustomerDTO("John", 22);
}
