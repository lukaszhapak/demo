package com.example.demo.test.unit.basics.mockito;

import static com.example.demo.test.unit.basics.TestData.getCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerRepository;
import com.example.demo.test.unit.basics.customer.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockitoExtensionTest {

  @Mock
  CustomerRepository customerRepository;
  @InjectMocks
  CustomerService customerService;

  @Test
  @DisplayName("verify save")
  void verifySave() {
	// given
	Customer customer = getCustomer();

	// when
	customerService.save(customer);

	// then
	verify(customerRepository).save(any());
  }
}
