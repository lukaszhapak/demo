package com.example.clinic.commons;

import com.example.commons.test.JdbcTestHelper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@TestConfiguration
public class ClinicTestConfig {

  @Bean
  public JdbcTestHelper jdbcTestHelper(final NamedParameterJdbcOperations jdbc) {
	return new JdbcTestHelper(jdbc);
  }
}
