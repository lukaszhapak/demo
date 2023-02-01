package com.example.demo.commons;

import java.text.MessageFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@RequiredArgsConstructor
public class JdbcTestHelper<T> {

  private final NamedParameterJdbcOperations jdbc;

  public List<T> fetchEntities(Long id, String tableName, Class<T> clazz) {
	return fetchEntities(id, tableName, "=", clazz);
  }

  public List<T> fetchEntities(String tableName, Class<T> clazz) {
	return fetchEntities(100000L, tableName, "<", clazz);
  }

  private List<T> fetchEntities(Long id, String tableName, String operator, Class<T> clazz) {
	return jdbc.query(
		MessageFormat.format("SELECT * FROM {0} WHERE ID {1} :id", tableName, operator),
		new MapSqlParameterSource()
			.addValue("id", id),
		new BeanPropertyRowMapper<T>(clazz));
  }
}
