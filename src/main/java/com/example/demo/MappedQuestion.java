package com.example.demo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappedQuestion {
  private String question;
  private List<String> correct;
  private List<String> other;
}
