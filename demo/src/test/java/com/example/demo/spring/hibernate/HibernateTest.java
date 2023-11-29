package com.example.demo.spring.hibernate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;

@SpringBootTest
class HibernateTest {

  @Autowired
  JdbcOperations jdbcOperations;

  @Autowired
  StudentService studentService;

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

  @Test
  @DisplayName("should save and find student")
  void shouldSaveAndFindStudent() {
	// given
	Long id = studentService.save(createStudent()).getId();

	// when
	Student student = studentService.findById(id);

	// then
	assertThat(student).usingRecursiveComparison().ignoringFields("id", "oneToOne.id", "oneToMany.id").isEqualTo(createStudent());
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
			.name("159")
			.build())
		.oneToMany(List.of(
			StudentOneToMany.builder().name("123").build(),
			StudentOneToMany.builder().name("456").build(),
			StudentOneToMany.builder().name("789").build()
		))
		.build();
  }
}