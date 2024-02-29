package com.example.demo.nonspring.test;

import static com.example.demo.nonspring.test.TestData.getStudent;
import static com.example.demo.nonspring.test.TestData.getStudentDTO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecursiveComparisonTest {

  @Test
  @DisplayName("recursive comparison")
  void recursiveComparison() {
	// Setup:
	Student student = getStudent();
	StudentDTO studentDTO = getStudentDTO();

	// When:

	// Then:
	assertThat(student).isNotEqualTo(studentDTO); // not equal, different class
	assertThat(studentDTO).usingRecursiveComparison().isEqualTo(student); // is equal because DTO have no id
	assertThat(student).usingRecursiveComparison().isNotEqualTo(studentDTO); // not equal because student have id
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDTO);
	assertThat(student).usingRecursiveComparison().ignoringActualNullFields().isNotEqualTo(studentDTO);
	assertThat(student).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(studentDTO);

	student.setName("Jim");
	assertThat(studentDTO).usingRecursiveComparison().isNotEqualTo(student);
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isNotEqualTo(studentDTO);
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
	List<StudentDTO> firstList = new ArrayList<>();
	List<StudentDTO> secondList = new ArrayList<>();

	// when
	firstList.add(new StudentDTO("John", 22));
	firstList.add(new StudentDTO("Jim", 24));

	secondList.add(new StudentDTO("Jim", 24));
	secondList.add(new StudentDTO("John", 22));

	// then
	assertThat(firstList).hasSameSizeAs(secondList);
	assertThat(firstList).usingRecursiveComparison().isNotEqualTo(secondList);
	assertThat(firstList).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(secondList);  // it already compares size of both lists
  }
}