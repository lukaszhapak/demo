package com.example.demo.nonspring.assertJ;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListTest {

  @Test
  @DisplayName("Test name")
  void testName() {
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
	List<StudentDTO> firstList = new ArrayList<>();
	List<StudentDTO> secondList = new ArrayList<>();

	// when
	firstList.add(new StudentDTO("John", 22));
	firstList.add(new StudentDTO("Jim", 24));

	secondList.add(new StudentDTO("Jim", 24));
	secondList.add(new StudentDTO("John", 22));

	// then
	assertThat(firstList).hasSameSizeAs(secondList).usingRecursiveComparison().isNotEqualTo(secondList);
	assertThat(firstList).hasSameSizeAs(secondList).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(secondList);
  }
}
