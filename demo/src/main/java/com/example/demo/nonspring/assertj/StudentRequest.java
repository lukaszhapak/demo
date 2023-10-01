package com.example.demo.nonspring.assertj;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class StudentRequest {

    private String name;
    private Integer age;
}
