package com.example.demo.test.basics.assertJ;

import static com.example.demo.test.basics.TestData.getCustomer;
import static com.example.demo.test.basics.TestData.getCustomerDTO;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.basics.Customer;
import com.example.demo.test.basics.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecursiveComparisonTest {

  @Test
  @DisplayName("recursive comparison")
  void recursiveComparison() {
	// Setup:
	Customer customer = getCustomer();
	CustomerDTO customerDTO = getCustomerDTO();

	// Then:
	assertThat(customer).isNotEqualTo(customerDTO); // not equal, different class
	assertThat(customerDTO).usingRecursiveComparison().isEqualTo(customer); // is equal because DTO have no id
	assertThat(customer).usingRecursiveComparison().isNotEqualTo(customerDTO); // not equal because customer have id
	assertThat(customer).usingRecursiveComparison().ignoringFields("id").isEqualTo(customerDTO);
	assertThat(customer).usingRecursiveComparison().ignoringActualNullFields().isNotEqualTo(customerDTO);
	assertThat(customer).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(customerDTO);

	customer.setName("Jim");
	assertThat(customerDTO).usingRecursiveComparison().isNotEqualTo(customer);
	assertThat(customer).usingRecursiveComparison().ignoringFields("id").isNotEqualTo(customerDTO);
  }

  @Test
  @DisplayName("int list")
  void intList() {
	// Setup:
	List<Integer> firstList = new ArrayList<>();
	List<Integer> secondList = new ArrayList<>();

	// When:
	firstList.add(2);
	firstList.add(5);
	firstList.add(4);

	secondList.add(4);
	secondList.add(2);
	secondList.add(5);

	// Then:
	assertThat(firstList).isNotEqualTo(secondList);
	assertThat(firstList).hasSameSizeAs(secondList).hasSameElementsAs(secondList);
  }

  @Test
  @DisplayName("objects list")
  void objectsList() {
	// given
	List<CustomerDTO> firstList = new ArrayList<>();
	List<CustomerDTO> secondList = new ArrayList<>();

	// when
	firstList.add(new CustomerDTO("John", 22));
	firstList.add(new CustomerDTO("Jim", 24));

	secondList.add(new CustomerDTO("Jim", 24));
	secondList.add(new CustomerDTO("John", 22));

	// then
	assertThat(firstList).hasSameSizeAs(secondList);
	assertThat(firstList).usingRecursiveComparison().isNotEqualTo(secondList);
	assertThat(firstList).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(secondList);  // it already compares size of both lists
  }
}