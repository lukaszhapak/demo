package com.example.demo.spring.jpql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JpqlTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  @Test
  @DisplayName("should save student and fetch name")
  void shouldSaveStudentAndFetchName() {
	// Setup:
	Student john = createStudent();
	studentService.save(john);

	// When:
	String studentName = studentService.findNameById(john.getId());

	// Then:
	assertThat(studentName).isEqualTo(john.getName());
  }

  @Test
  @DisplayName("should save students and fetch list")
  void shouldSaveStudentsAndFetchList() {
	// given
	studentService.save(createStudent());
	studentService.save(createStudent());

	// when
	List<StudentDTO> studentDTOS = studentService.findAllAsDTOs();

	// then
	assertThat(studentDTOS.size()).isGreaterThan(1);
  }

  @Test
  @DisplayName("should save find student name")
  void shouldSaveFindStudentName() {
	// given
	Long id = studentService.save(createStudent()).getId();

	// when
	String name = studentService.findNameById(id);

	// then
	assertThat(name).isEqualTo("John");
  }

  @Test
  @DisplayName("should save students and find as DTOs")
  void shouldSaveStudentsAndFindAsDtOs() {
	// given
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());

	// when
	List<StudentDTO> allAsDTOs = studentService.findAllAsDTOs();

	// then
	assertThat(allAsDTOs.size()).isGreaterThan(4);
  }

  @Test
  @DisplayName("should save student and find by street name")
  void shouldSaveStudentAndFindByStreetName() {
	// given
	String streetName = "test_street_name";
	Student student = createStudent();
	student.getAddress().setStreetName(streetName);
	studentService.save(student);
	studentService.save(createStudent());

	// when
	Student result = studentService.findByAddressStreetName(streetName);

	// then
	assertThat(result.getAddress().getStreetName()).isEqualTo(streetName);
  }

  private Student createStudent() {
	return Student.builder()
		.age(22)
		.name("John")
		.address(Address.builder()
			.streetName("street")
			.flatNumber("22")
			.build())
		.build();
  }
}
