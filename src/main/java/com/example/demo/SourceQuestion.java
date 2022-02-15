package com.example.demo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceQuestion {
  private String  query;
  private List<String> answers;
  private String solution;
}
