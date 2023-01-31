package com.example.demo.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelperClass {

  public static String collectionAsString(List<Integer> collection) {
	return collection == null ? "" : collection.stream()
		.map(String::valueOf)
		.collect(Collectors.joining(","));
  }

  public static List<Integer> stringAsCollection(String string) {
	return string == null ? new ArrayList<>() : Arrays.stream(string.split(","))
		.map(Integer::parseInt)
		.collect(Collectors.toList());
  }
}
