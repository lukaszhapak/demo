package com.example.demo.nonspring.immutable;

import java.util.List;

class ImmutableDemo {

  public static void main(String[] args) {
	ImmutableStudent john = new ImmutableStudent("John");


	List<String> names = List.of("John", "James");
//	names.remove("Michael");
//	names.add("Michael");
  }
}
