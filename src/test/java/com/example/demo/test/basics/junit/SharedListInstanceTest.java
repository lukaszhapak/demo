package com.example.demo.test.basics.junit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SharedListInstanceTest {

  // object instance is being recreated for each test
  private final List<Integer> list = new ArrayList<>();

  @Test
  @DisplayName("add one number to list")
  void addOneNumberToList() {
	// given
	list.add(2);

	// then
	assertThat(list.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("add two numbers to list")
  void addTwoNumbersToList() {
	// given
	list.add(6);
	list.add(3);

	// then
	assertThat(list.size()).isEqualTo(2);
  }
}
