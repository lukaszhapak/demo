package com.example.demo.nonspring.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Post {

  private Long userId;
  private Long id;
  private String title;
  private String body;

}
