package com.example.demo.commons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelperClass {

  public static String collectionAsString(List<Integer> collection) {
	return collection == null ? null : collection.stream()
		.map(String::valueOf)
		.collect(Collectors.joining(","));
  }

  public static List<Integer> stringAsCollection(String string) {
	return string == null ? null : Arrays.stream(string.split(","))
		.map(Integer::parseInt)
		.collect(Collectors.toList());
  }
}
