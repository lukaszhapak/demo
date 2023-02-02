package com.example.demo.commons.helper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface MappingHelper {

  static String collectionAsString(List<Integer> collection) {
	if (collection == null) {
	  return null;
	} else if (collection.isEmpty()) {
	  return "";
	} else {
	  return collection.stream()
		  .map(String::valueOf)
		  .collect(Collectors.joining(","));
	}
  }

  static List<Integer> stringAsCollection(String string) {
	if (string == null) {
	  return null;
	} else if (string.equals("")) {
	  return Collections.emptyList();
	} else {
	  return Arrays.stream(string.split(","))
		  .map(Integer::parseInt)
		  .collect(Collectors.toList());
	}
  }
}
