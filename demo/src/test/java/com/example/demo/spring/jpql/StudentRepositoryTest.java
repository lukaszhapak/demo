package com.example.demo.spring.jpql;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StudentRepositoryTest  extends AbstractIntegrationTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @DisplayName("should save student and fetch name")
    void shouldSaveStudentAndFetchName() {
        // Setup:
        Student john = getStudent();
        studentRepository.save(john);

        // When:
        String studentName = studentRepository.selectName(john.getId());

        // Then:
        assertThat(studentName).isEqualTo(john.getName());
    }

    private Student getStudent() {
        return Student.builder()
                .age(22)
                .name("John")
                .build();
    }
}
