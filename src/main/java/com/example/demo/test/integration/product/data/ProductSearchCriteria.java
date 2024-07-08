package com.example.demo.test.integration.product.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchCriteria {

  String sortBy;
  Boolean sortAscending;
  Integer page;
  Integer size;

  Integer id;
  String name;
  Integer minIntegerValue;
  Integer maxIntegerValue;
}
