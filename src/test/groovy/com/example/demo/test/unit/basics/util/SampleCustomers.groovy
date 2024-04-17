package com.example.demo.test.unit.basics.util

import com.example.demo.test.unit.basics.customer.CustomerDTO

trait SampleCustomers {

    // by name
    CustomerDTO john = new CustomerDTO("John", 22)
    CustomerDTO jim = new CustomerDTO("Jim", 26)
    CustomerDTO michael = new CustomerDTO("Michael", 32)

    // by number
    CustomerDTO customer1 = new CustomerDTO("John", 22)
    CustomerDTO customer2 = new CustomerDTO("Jim", 26)
    CustomerDTO customer3 = new CustomerDTO("Michael", 32)

    // or by some other parameter
    CustomerDTO validCustomer = new CustomerDTO("John", 22)
    CustomerDTO oldCustomer = new CustomerDTO("John", 78)
    CustomerDTO customerWithRegisteredProduct = new CustomerDTO("John", 22)
    CustomerDTO customerWithAlreadyPaidOrder = new CustomerDTO("John", 22)
}