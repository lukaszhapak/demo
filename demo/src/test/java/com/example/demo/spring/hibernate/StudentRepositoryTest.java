package com.example.demo.spring.hibernate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;

@SpringBootTest
class StudentRepositoryTest {

  @Autowired
  JdbcOperations jdbcOperations;

  @Autowired
  StudentRepository studentRepository;

  @Test
  @Transactional
  @DisplayName("should save and fetch student")
  void shouldSaveAndFetchStudent() {
	// given
	Long id = studentRepository.save(createStudent()).getId();

	// when
	Student student = studentRepository.findById(id).get();

	// then
	assertThat(student).usingRecursiveComparison().ignoringFields("id", "oneToOne.id", "oneToMany.id").isEqualTo(createStudent());
  }

  @Test
  @DisplayName("should fetch table names")
  void shouldFetchTableNames() {
	// given
	String query = "SHOW TABLES";

	// when
	List<Object> tables = jdbcOperations.queryForList(query).stream().map(Map::values).map(x -> x.stream().findFirst().get()).collect(Collectors.toList());

	// then
	assertThat(tables.size()).isEqualTo(4);
	System.out.println(tables);
  }

  Student createStudent() {
	return Student.builder()
		.firstName("John")
		.lastName("Doe")
		.age(24)
		.gradesArray(new Integer[]{1, 2, 3, 4, 5, 6})
		.gradesList(List.of(6, 5, 4, 3, 2, 1))
		.address(Address.builder()
			.streetName("Street")
			.flatNumber("22")
			.build())
		.oneToOne(StudentOneToOne.builder()
			.name("Asd")
			.build())
		.oneToMany(List.of(
			StudentOneToMany.builder().name("32111").build(),
			StudentOneToMany.builder().name("32331").build(),
			StudentOneToMany.builder().name("32121").build()
		))
		.build();
  }
}