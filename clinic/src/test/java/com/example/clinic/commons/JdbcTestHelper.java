package com.example.clinic.commons;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

@Component
public class JdbcTestHelper<T> {

  @Autowired
  private NamedParameterJdbcOperations jdbc;

  public List<T> fetchEntities(String tableName, String operator, Class<T> clazz) {
	return jdbc.query(
		MessageFormat.format("SELECT * FROM {0} WHERE ID {1} :id", tableName, operator),
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
