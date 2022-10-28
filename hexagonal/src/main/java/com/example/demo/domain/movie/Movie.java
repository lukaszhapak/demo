package com.example.demo.domain.movie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {

  private Long id;
  private String title;
  private String author;
}
