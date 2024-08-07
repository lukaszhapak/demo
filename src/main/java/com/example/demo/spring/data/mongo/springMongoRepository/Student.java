package com.example.demo.spring.data.mongo.springMongoRepository;

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
}
