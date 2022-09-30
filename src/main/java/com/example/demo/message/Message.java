package com.example.demo.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Message {

  private String author;
  private String title;
  private String body;
}
