package com.example.demo.nonspring.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CollectionsDemo {

  public static void main(String[] args) {

	List<String> arrayList = new ArrayList<>();

	List<String> linkedList = new LinkedList<>();

	linkedList.add("asd");
	linkedList.add("asd2");
	linkedList.add("asd21");
	linkedList.add("asd213");

	String s = linkedList.get(2);

	Set<String> hashSet = new HashSet<>();

	Map<String, String> hashMap = new HashMap<>();
	hashMap.put("1", "123");
  }
}
