package com.example.demo.commons;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import io.restassured.response.Response;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest<T> {

  @Autowired
  protected NamedParameterJdbcOperations jdbc;

  @LocalServerPort
  protected int port;

  protected Response getHttpCall(String url) {
	return given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  protected Response postHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected Response deleteHttpCall(String url) {
	return given()
		.port(port)
		.contentType(JSON)
		.log().all()
		.when()
		.delete(url);
  }

  protected Response putHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.put(url);
  }

  protected List<T> fetchEntities(Long id, String tableName, Class<T> clazz) {
	return fetchEntities(id, tableName, "=", clazz);
  }

  protected List<T> fetchEntities(String tableName, Class<T> clazz) {
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
