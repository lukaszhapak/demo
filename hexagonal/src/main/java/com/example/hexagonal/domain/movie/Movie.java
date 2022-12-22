package com.example.hexagonal.domain.movie;

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
