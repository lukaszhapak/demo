package com.example.demo;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentChild {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    //        @ManyToOne
    @ManyToOne(fetch = LAZY)
    private Student student;

    public StudentChild(String name) {
        this.name = name;
    }
}
