package com.example.demo.spring.data.jpaDataTypes

import com.example.demo.common.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcOperations

import java.util.stream.Collectors

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class JpaDataTypesSpec extends IntegrationSpec {

    @Autowired
    JdbcOperations jdbcOperations

    @Autowired
    StudentRepository studentRepository

    def "should fetch table"() {
        expect:
        fetchTables() == ["STUDENT", "STUDENT_GRADES_LIST", "STUDENT_ONE_TO_MANY", "STUDENT_ONE_TO_ONE"]
    }

    def "should save and find student"() {
        given:
        Long id = studentRepository.save(createStudent()).getId()

        when:
        Student student = studentRepository.findByIdFetchingOneToMany(id).get()

        then:
        assertThat(student).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(createStudent())
    }

    List<String> fetchTables() {
        jdbcOperations.queryForList("SHOW TABLES")
                .stream()
                .map(Map::values)
                .map(x -> x.stream().findFirst().get())
                .collect(Collectors.toList())
    }

    Student createStudent() {
        Student.builder()
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
                .build()
    }
}