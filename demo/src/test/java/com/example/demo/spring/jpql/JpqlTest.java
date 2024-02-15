package com.example.demo.spring.jpql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JpqlTest extends AbstractIntegrationTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should save student and fetch name")
  void shouldSaveStudentAndFetchName() {
	// Setup:
	Student john = createStudent();
	studentRepository.save(john);

	// When:
	String studentName = studentRepository.findNameById(john.getId());

	// Then:
	assertThat(studentName).isEqualTo(john.getName());
  }

  @Test
  @DisplayName("should save students and fetch list")
  void shouldSaveStudentsAndFetchList() {
	// given
	studentRepository.saveAll(List.of(createStudent(), createStudent()));

	// when
	List<StudentDTO> studentDTOS = studentRepository.findAllAsDTOs();

	// then
	assertThat(studentDTOS.size()).isGreaterThan(1);
  }

  @Test
  @DisplayName("should save and find student name")
  void shouldSaveAndFindStudentName() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	String name = studentRepository.findNameById(id);

	// then
	assertThat(name).isEqualTo("John");
  }

  @Test
  @DisplayName("should save students and find as DTOs")
  void shouldSaveStudentsAndFindAsDTOs() {
	// given
	studentRepository.saveAll(List.of(createStudent(), createStudent(), createStudent(), createStudent(), createStudent()));

	// when
	List<StudentDTO> allAsDTOs = studentRepository.findAllAsDTOs();

	// then
	assertThat(allAsDTOs.size()).isGreaterThan(4);
  }

  @Test
  @DisplayName("should save students and find as DTOs")
  void shouldSaveStudentAndFindAsDTOs() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	StudentDTO studentDTO = studentRepository.findByIdAsDTOs(id);

	// then
	assertThat(studentDTO.getName()).isEqualTo("John");
  }

  @Test
  @DisplayName("should save student and find by street name")
  void shouldSaveStudentAndFindByStreetName() {
	// given
	String streetName = "test_street_name#123";
	Student student = createStudent();
	student.getAddress().setStreetName(streetName);
	studentRepository.saveAll(List.of(createStudent(), student));

	// when
	List<Student> result = studentRepository.findByAddressStreetName(streetName);

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
	studentRepository.saveAll(List.of(createStudent(), student));

	// when
	List<Student> result = studentRepository.findByAddressStreetNameAndFlatNumber(streetName, flatNumber);

	// then
	assertThat(result).hasSize(1);
	assertThat(result.get(0).getAddress().getStreetName()).isEqualTo(streetName);
	assertThat(result.get(0).getAddress().getFlatNumber()).isEqualTo(flatNumber);
  }

  @Test
  @DisplayName("should save student and get name and age")
  void shouldSaveStudentAndGetNameAndAge() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	String nameAndAgeById = studentRepository.findNameAndAgeById(id);

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
