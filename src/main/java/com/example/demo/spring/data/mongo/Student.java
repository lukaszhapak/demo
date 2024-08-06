package com.example.demo.spring.data.mongo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@Document
class Student {

  @MongoId
  private String id;
  private String name;
  private int age;
  private LocalDateTime created;
}
