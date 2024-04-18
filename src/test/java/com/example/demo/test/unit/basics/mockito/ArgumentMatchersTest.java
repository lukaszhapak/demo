package com.example.demo.test.unit.basics.mockito;

import static com.example.demo.test.unit.basics.TestData.getCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerRepository;
import com.example.demo.test.unit.basics.customer.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArgumentMatchersTest {

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
	verify(customerRepository).save(argThat(arg -> arg.getName().equals("John")
		&& arg.getAge() == (22)
	));
	// for many fields argument captor with recursive comparison is better
  }

  @Test
  @DisplayName("should save customer with argument matcher")
  void shouldSaveCustomerWithArgumentMatcher() {
	// given
	Customer customer = getCustomer();
	when(customerRepository.save(customer)).thenReturn(getCustomer()); // it requires same object reference

	// when
	Customer response = customerService.save(customer);

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should save customer with matcher on name")
  void shouldSaveCustomerWithArgumentMatcherOnName() {
	// given
	when(customerRepository.save(argThat(arg -> arg.getName().equals("John")))).thenReturn(getCustomer());

	// when
	Customer response = customerService.save(getCustomer());

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get customer by name")
  void shouldGetCustomerByName() {
	// given
	when(customerRepository.findByName("John")).thenReturn(getCustomer());

	// when
	Customer response = customerService.findByName("John");

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get customer by id")
  void shouldGetCustomerById() {
	// given
	when(customerRepository.findById(12L)).thenReturn(getCustomer());

	// when
	Customer response = customerService.findById(12L);

	// then
	assertThat(response.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should get customer by name and id")
  void shouldGetCustomerByNameAndId() {
	// given
	when(customerRepository.findByNameAndId(any(), eq(12L))).thenReturn(getCustomer());  // eq() required here because other argument uses any()

	// when
	Customer response = customerService.findByNameAndId("John", 12L);

	// then
	assertThat(response.getName()).isEqualTo("John");
  }
}