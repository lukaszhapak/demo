package com.example.demo.commons.helper;

import java.util.List;

public interface TestStorageHelper<T> {

  void insertData(List<T> data);

  void cleanData();

  List<T> fetchEntities(String tableName, Class<T> clazz);

  T fetchEntity(Long id, String tableName, Class<T> clazz);

  Boolean existsById(Long id, String tableName);

}
