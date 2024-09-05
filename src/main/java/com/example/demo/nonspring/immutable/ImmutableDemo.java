package com.example.demo.nonspring.immutable;

import java.util.List;

class ImmutableDemo {

  public static void main(String[] args) {
	Student john = new Student("John");

	String name = john.getName();

	name.intern();

	List<String> names = List.of("John", "James");
//	names.remove("Michael");
//	names.add("Michael");
  }
}
