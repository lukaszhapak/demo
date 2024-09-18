package com.example.demo.nonspring.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;

class CollectionsDemo {

  public static void main(String[] args) {

	Object asd = new Object();

	// list
	ArrayList<String> arrayList = new ArrayList<>();
	LinkedList<String> linkedList = new LinkedList<>();

	linkedList.add("asd");
	linkedList.add("asd2");
	linkedList.add("asd21");
	linkedList.add("asd213");

	String s = linkedList.get(2);


	// queue
	Queue<String> arrayDeque = new ArrayDeque<>();
	Queue<String> lindkedListAsDeque = new LinkedList<>();


	// set
	HashSet<String> hashSet = new HashSet<>();
	LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
	TreeSet<String> treeSet = new TreeSet<>();


	// map
	HashMap<String, String> hashMap = new HashMap<>();
	LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
	TreeMap<String, String> treeMap = new TreeMap<>();

	hashMap.put("1", "123");
	String s1 = hashMap.get("1");
  }
}
