package com.example.demo.spring.data.mongo.mongoSearch;

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
  private String firstName;
  private String lastName;
  private int age;

  private Address address;
  private LocalDateTime date;
}
