package com.example.demo.test.basics.prepareTestData;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.test.basics.Customer;
import com.example.demo.test.basics.TestData;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class ReflectionTestUtilsTest {

  @Test
  @DisplayName("should get customer")
  void shouldGetCustomer() {
	// given
	Customer customer = getCustomer(Map.of("name", "Invalid name"));

	// then
	assertThat(customer.getName()).isEqualTo("Invalid name");
  }

  static Customer getCustomer(Map<String, Object> fieldsValues) {
	Customer customer = TestData.getCustomer();
	fieldsValues.forEach((key, value) -> ReflectionTestUtils.setField(customer, key, value));
	return customer;
  }
}
