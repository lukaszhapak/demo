package com.example.demo.assertj;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class StudentResponse {

    private Long id;
    private String name;
    private Integer age;
}