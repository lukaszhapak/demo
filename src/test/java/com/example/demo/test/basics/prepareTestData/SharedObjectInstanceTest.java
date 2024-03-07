package com.example.demo.test.basics.prepareTestData;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.basics.CustomerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SharedObjectInstanceTest {

  // object instance is being recreated for each test
  // those instances can be stored in interface, so test classes can implement that
  CustomerDTO john = new CustomerDTO("John", 22);
  CustomerDTO Jim = new CustomerDTO("Jim", 26);
  CustomerDTO Michael = new CustomerDTO("Michael", 32);

  @Test
  @DisplayName("get customer")
  void getCustomer() {
	// given
	john.setName("Invalid name");

	// when
	// perform some action with object instance

	// then
	assertThat(john.getName()).isEqualTo("Invalid name");
  }

}
