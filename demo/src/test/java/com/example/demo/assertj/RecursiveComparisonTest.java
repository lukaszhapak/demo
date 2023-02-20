package com.example.demo.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecursiveComparisonTest {


    @Test
    @DisplayName("recursive comparison")
    void recursiveComparison() {
        // Setup:
        StudentRequest studentRequest = getStudentRequest();
        StudentResponse studentResponse = getStudentResponse();

        // When:

        // Then:
        assertThat(studentRequest).isNotEqualTo(studentResponse);
        assertThat(studentRequest).usingRecursiveComparison().isEqualTo(studentResponse);
        assertThat(studentResponse).usingRecursiveComparison().isNotEqualTo(studentRequest);
        assertThat(studentResponse).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentRequest);
        assertThat(studentResponse).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(studentRequest);

        studentRequest.setName("Jim");
        assertThat(studentRequest).usingRecursiveComparison().isNotEqualTo(studentResponse);
    }

    private static StudentRequest getStudentRequest() {
        return StudentRequest.builder()
                .name("John")
                .age(22)
                .build();
    }

    private static StudentResponse getStudentResponse() {
        return StudentResponse.builder()
                .id(14L)
                .name("John")
                .age(22)
                .build();
    }
}