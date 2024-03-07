package com.example.demo.test.basics.mockito;

import static com.example.demo.test.basics.TestData.getCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.test.basics.Customer;
import com.example.demo.test.basics.CustomerRepository;
import com.example.demo.test.basics.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ArgumentCaptorTest {

  final ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
  final CustomerRepository customerRepository = mock(CustomerRepository.class);
  final CustomerService customerService = new CustomerService(customerRepository);

  @Test
  @DisplayName("should save customer")
  void shouldSaveCustomer() {
	// given
	Customer customer = getCustomer();

	// when
	customerService.save(customer);

	// then
	verify(customerRepository).save(customerArgumentCaptor.capture());
	Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();

	assertThat(customerArgumentCaptorValue.getName()).isEqualTo("John"); // comparing fields one by one
	assertThat(customerArgumentCaptorValue.getAge()).isEqualTo(22);

	assertThat(customerArgumentCaptorValue).usingRecursiveComparison().isEqualTo(customer); // or all at once using recursive comparison
  }
}