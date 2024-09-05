package com.example.demo.spring.modules.data.mongo.base;

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
public class MongoStudent {

  @MongoId
  private String id;
  private String firstName;
  private String lastName;
  private int age;

  private MongoAddress address;
  private LocalDateTime date;
}
