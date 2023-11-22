package com.example.demo.spring.jpql;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
}
