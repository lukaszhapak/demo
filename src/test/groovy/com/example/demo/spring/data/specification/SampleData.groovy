package com.example.demo.spring.data.specification


import java.time.LocalDateTime

trait SampleData {

    Student john = Student.builder()
            .firstName("John")
            .lastName("Doe")
            .age(24)
            .date(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Oak street")
                    .flatNumber("51")
                    .build())
            .build();

    Student jim = Student.builder()
            .firstName("Jim")
            .lastName("Newman")
            .age(21)
            .date(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
            .address(Address.builder()
                    .streetName("School street")
                    .flatNumber("12")
                    .build())
            .build();

    Student michael = Student.builder()
            .firstName("Michael")
            .lastName("Smith")
            .age(27)
            .date(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Student street")
                    .flatNumber("123")
                    .build())
            .build();

    StudentSearchCriteria studentSearchCriteria = StudentSearchCriteria.builder()
            .page(0)
            .size(10)
            .sortBy("id")
            .build();
}
