package com.example.demo.test.unit.basics.util

import com.example.demo.test.unit.basics.customer.Customer
import com.example.demo.test.unit.basics.customer.CustomerRepository

class TestCustomerRepository implements CustomerRepository {

    private Map<Long, Customer> map = new HashMap<>()
    private Long id = 0L

    @Override
    Customer save(Customer customer) {
        if (customer.getId() == null || !map.containsKey(customer.getId())) {
            setId(customer)
        }
        map.put(customer.getId(), customer)
        return customer
    }

    @Override
    Customer findByName(String name) {
        map.values().stream()
                .filter { it -> it.getName() == name }
                .findFirst().get()
    }

    @Override
    Customer findById(Long id) {
        map.get(id)
    }

    @Override
    Customer findByNameAndId(String name, Long id) {
        map.values().stream()
                .filter { it -> it.getName() == name && it.getId() == id }
                .findFirst().get()
    }

    private void setId(Customer customer) {
        customer.setId(++id)
    }
}
