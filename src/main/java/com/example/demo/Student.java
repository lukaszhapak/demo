package com.example.demo;

import lombok.*;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "student", cascade = ALL)
    private Set<StudentChild> studentChildren = new HashSet<>();

    public Student(String name) {
        this.name = name;
    }

    public void addChild(StudentChild studentChild) {
        studentChild.setStudent(this);
        studentChildren.add(studentChild);
    }
}
