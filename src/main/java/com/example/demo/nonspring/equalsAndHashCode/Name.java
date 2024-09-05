package com.example.demo.nonspring.equalsAndHashCode;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
class Name {

  String name;


  @Override
  public boolean equals(Object o) {
	if (this == o) {
	  return true;
	}
	if (o == null || getClass() != o.getClass()) {
	  return false;
	}
	Name name1 = (Name) o;
	return Objects.equals(name, name1.name);
  }

  @Override
  public int hashCode() {
	return Objects.hashCode(name);
  }
}
