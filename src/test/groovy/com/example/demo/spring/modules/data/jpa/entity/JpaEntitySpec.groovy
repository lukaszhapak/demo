package com.example.demo.spring.modules.data.jpa.entity

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcOperations

import java.util.stream.Collectors

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class JpaEntitySpec extends AbstractIntegrationSpec {

    @Autowired
    JdbcOperations jdbcOperations

    @Autowired
    MappingsStudentRepository studentRepository

    // todo

//    def "should fetch table"() {
//        expect:
//        fetchTables().contains(["MAPPINGS_STUDENT", "STUDENT_GRADES_LIST", "STUDENT_ONE_TO_MANY", "STUDENT_ONE_TO_ONE"])
//    }

    def "should save and find student with all fields"() {
        given:
        Long id = studentRepository.save(createStudent()).getId()

        when:
        MappingsStudent student = studentRepository.findByIdFetchingOneToMany(id).get()

        then:
        assertThat(student).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(createStudent())
    }

    def "should save and find student with min fields"() {
        given:
        Long id = studentRepository.save(new MappingsStudent().setFirstName("Jim").setAge(22)).id

        expect:
        studentRepository.findById(id).get()
    }


    def "should save and update student with min fields"() {
        given:
        MappingsStudent student = studentRepository.save(new MappingsStudent().setFirstName("Jim").setAge(22))

        when:
        student.setFirstName("Michael")
        studentRepository.save(student)

        then:
        studentRepository.findById(student.id).get().firstName == "Michael"
    }

    List<String> fetchTables() {
        jdbcOperations.queryForList("SHOW TABLES")
                .stream()
                .map(Map::values)
                .map(x -> x.stream().findFirst().get())
                .collect(Collectors.toList())
    }

    MappingsStudent createStudent() {
        new MappingsStudent()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(24)
                .setGradesArray(new Integer[]{1, 2, 3, 4, 5, 6})
                .setGradesList([6, 5, 4, 3, 2, 1])
                .setAddress(new MappingsAddress()
                        .setStreetName("Street")
                        .setFlatNumber("22"))
                .setOneToOne(new StudentOneToOne()
                        .setName("159"))
                .setOneToMany([
                        new StudentOneToMany().setName("123"),
                        new StudentOneToMany().setName("456"),
                        new StudentOneToMany().setName("789")]
                )
    }
}