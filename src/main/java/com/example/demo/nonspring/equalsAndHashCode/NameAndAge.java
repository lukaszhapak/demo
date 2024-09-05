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
class NameAndAge {

  String name;
  int age;

  @Override
  public boolean equals(Object o) {
	if (this == o) {
	  return true;
	}
	if (o == null || getClass() != o.getClass()) {
	  return false;
	}
	NameAndAge that = (NameAndAge) o;
	return age == that.age && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
	return Objects.hash(name, age);
  }
}
