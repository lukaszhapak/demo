package com.example.demo.spring.hibernate;

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
class StudentOneToMany {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
