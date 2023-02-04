package com.example.clinic.commons;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageDTO <T> {

  private Long page;
  private Integer size;
  private List<T> data;
}
