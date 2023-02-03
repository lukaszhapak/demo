package com.example.demo.commons;

import com.example.demo.commons.helper.TestStorageHelper;
import java.text.MessageFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@RequiredArgsConstructor
public class JdbcTestHelper<T> implements TestStorageHelper<T> {

  private final NamedParameterJdbcOperations jdbc;

  @Override
  public void insertData(List<T> data) {
	// got sql scripts for that
  }

  @Override
  public void cleanData() {
	// got sql scripts for that
  }

  public List<T> fetchEntities(String tableName, Class<T> clazz) {
	return jdbc.query(
		MessageFormat.format("SELECT * FROM {0} WHERE ID < :id", tableName),
		new MapSqlParameterSource().addValue("id", 100000L),
		new BeanPropertyRowMapper<T>(clazz));
  }

  public T fetchEntity(Long id, String tableName, Class<T> clazz) {
	return jdbc.queryForObject(
		MessageFormat.format("SELECT * FROM {0} WHERE ID = :id", tableName),
		new MapSqlParameterSource().addValue("id", id),
		new BeanPropertyRowMapper<T>(clazz));
  }

  public Boolean existsById(Long id, String tableName) {
	return jdbc.queryForObject(
		MessageFormat.format("SELECT EXISTS(SELECT 1 FROM {0} WHERE ID= :id)", tableName),
		new MapSqlParameterSource().addValue("id", id),
		Boolean.class);
  }
}
