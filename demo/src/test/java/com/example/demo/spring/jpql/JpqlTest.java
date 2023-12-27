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
	studentService.saveAll(List.of(createStudent(), createStudent()));

	// when
	List<StudentDTO> studentDTOS = studentService.findAllAsDTOs();

	// then
	assertThat(studentDTOS.size()).isGreaterThan(1);
  }

  @Test
  @DisplayName("should save and find student name")
  void shouldSaveAndFindStudentName() {
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
	studentService.saveAll(List.of(createStudent(), createStudent(), createStudent(), createStudent(), createStudent()));

	// when
	List<StudentDTO> allAsDTOs = studentService.findAllAsDTOs();

	// then
	assertThat(allAsDTOs.size()).isGreaterThan(4);
  }

  @Test
  @DisplayName("should save student and find by street name")
  void shouldSaveStudentAndFindByStreetName() {
	// given
	String streetName = "test_street_name#123";
	Student student = createStudent();
	student.getAddress().setStreetName(streetName);
	studentService.saveAll(List.of(createStudent(), student));

	// when
	List<Student> result = studentService.findByAddressStreetName(streetName);

	// then
	assertThat(result).hasSize(1);
	assertThat(result.get(0).getAddress().getStreetName()).isEqualTo(streetName);
  }

  @Test
  @DisplayName("should save student and find by street name and flat number")
  void shouldSaveStudentAndFindByStreetNameAndFlatNumber() {
	// given
	String streetName = "test_street_name#456";
	String flatNumber = "51";
	Student student = createStudent();
	student.getAddress().setStreetName(streetName);
	student.getAddress().setFlatNumber(flatNumber);
	studentService.saveAll(List.of(createStudent(), student));

	// when
	List<Student> result = studentService.findByAddressStreetNameAndFlatNumber(streetName, flatNumber);

	// then
	assertThat(result).hasSize(1);
	assertThat(result.get(0).getAddress().getStreetName()).isEqualTo(streetName);
	assertThat(result.get(0).getAddress().getFlatNumber()).isEqualTo(flatNumber);
  }

  @Test
  @DisplayName("should save student and get name and age")
  void shouldSaveStudentAndGetNameAndAge() {
	// given
	Long id = studentService.save(createStudent()).getId();

	// when
	String nameAndAgeById = studentService.findNameAndAgeById(id);

	// then
	assertThat(nameAndAgeById).isEqualTo("John,22");
  }

  private Student createStudent() {
	return Student.builder()
		.age(22)
		.name("John")
		.address(Address.builder()
			.streetName("street")
			.flatNumber("25")
			.build())
		.build();
  }
}
