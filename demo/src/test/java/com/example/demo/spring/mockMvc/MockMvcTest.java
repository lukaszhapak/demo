package com.example.demo.spring.mockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractMockMvcIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MockMvcTest extends AbstractMockMvcIntegrationTest {

  private static final String URL = "/mock-mvc/student/";

  @Test
  @DisplayName("should post get and delete student")
  void shouldPostGetAndDeleteStudent() throws Exception {
	// given
	Student request = Student.builder().name("John").age(25).build();
	Long id = postHttpCall(URL, request, Student.class, 200).getId();

	// when
	Student response = getHttpCall(URL + id, Student.class, 200);
	deleteHttpCall(URL + id, 200);

	// then
	assertThat(response).usingRecursiveComparison()
		.ignoringFields("id")
		.isEqualTo(request);
  }

  @Test
  @DisplayName("should post and get students")
  void shouldPostAndGetStudents() throws Exception {
	// given
	Student jim = Student.builder().name("Jim").age(22).build();
	Student john = Student.builder().name("John").age(25).build();
	postHttpCall(URL, john, Student.class, 200);
	postHttpCall(URL, jim, Student.class, 200);

	// when
	List<Student> studentsResponse = List.of(getHttpCall(URL, Student[].class, 200));

	// then
	assertThat(studentsResponse).usingRecursiveComparison()
		.ignoringFields("id")
		.ignoringCollectionOrder()
		.isEqualTo(List.of(jim, john));
  }
}