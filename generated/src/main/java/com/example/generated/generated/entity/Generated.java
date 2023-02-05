package com.example.generated.generated.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Generated {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;

}

