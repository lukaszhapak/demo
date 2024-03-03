package com.example.demo.test.basics.junit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.test.basics.Customer;
import com.example.demo.test.basics.CustomerValidator;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParameterizedTestTest {

  CustomerValidator customerValidator = new CustomerValidator();

  @ParameterizedTest
  @NullSource
  void nullSource(String input) {
	assertThat(input).isNull();
  }

  @ParameterizedTest
  @EmptySource
  void emptySource(String input) {
	assertThat(input).isEqualTo("");
  }

  @ParameterizedTest
  @NullAndEmptySource
  void nullAndEmptySource(String input) {
	assertThat(Strings.isBlank(input)).isTrue();
  }

  @ParameterizedTest
  @CsvSource({
	  "2,3,5",
	  "2,2,4",
	  "3,1,4"
  })
  void csvSource(int a, int b, int c) {
	assertThat(a + b).isEqualTo(c);
  }

  @ParameterizedTest
  @ValueSource(strings = {"a", "0", "this string has more than 64 characters--------------------------"})
  @DisplayName("value source")
  void valueSource(String name) {
	// given
	Customer customer = Customer.builder().name(name).age(24).build();

	// when
	boolean response = customerValidator.validate(customer);

	// then
	assertThat(response).isFalse();
  }

  static Stream<Arguments> getArguments() {
	return Stream.of(
		Arguments.of(12, true),
		Arguments.of(7, true),
		Arguments.of(3, false),
		Arguments.of(0, false)
	);
  }

  @ParameterizedTest
  @MethodSource("getArguments")
  void methodSource(int number, boolean expected) {
	assertThat(number > 5).isEqualTo(expected);
  }

  static Stream<Arguments> getArguments2() {
	return Stream.of(
		Arguments.of(12, List.of(2, 4, 12)),
		Arguments.of(7, List.of(2, 4, 7)),
		Arguments.of(3, List.of(8, 4, 3)),
		Arguments.of(0, List.of(2, 4, 0))
	);
  }

  @ParameterizedTest
  @MethodSource("getArguments2")
  void methodSource2(int number, List<Integer> list) {
	assertThat(list.get(2)).isEqualTo(number);
  }

  static Stream<Arguments> getArguments3() {
	return Stream.of(
		Arguments.of("John", new Customer("John", 21)),
		Arguments.of("Jim", new Customer("Jim", 24))
	);
  }

  @ParameterizedTest
  @MethodSource("getArguments3")
  void methodSource3(String name, Customer customer) {
	assertThat(customer.getName()).isEqualTo(name);
  }
}
