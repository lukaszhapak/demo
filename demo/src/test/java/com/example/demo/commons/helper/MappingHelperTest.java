package com.example.demo.commons.helper;

import static com.example.demo.commons.helper.MappingHelper.collectionAsString;
import static com.example.demo.commons.helper.MappingHelper.stringAsCollection;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MappingHelperTest {

  @Test
  @DisplayName("should convert collection to string")
  void shouldConvertCollectionToString() {
	// given
	List<Integer> integers = List.of(1, 2, 3, 4);
	String expected = "1,2,3,4";

	// when
	String actual = collectionAsString(integers);

	// then
	assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("should convert string to collection")
  void shouldConvertStringToCollection() {
	// given
	String string = "1,2,3,4";
	List<Integer> expected = List.of(1, 2, 3, 4);

	// when
	List<Integer> actual = stringAsCollection(string);

	// then
	assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("should not fail on null values")
  void shouldNotFailOnNullValues() {
	collectionAsString(null);
	stringAsCollection(null);
  }
}