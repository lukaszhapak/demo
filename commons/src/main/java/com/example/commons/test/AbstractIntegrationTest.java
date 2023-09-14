package com.example.commons.test;

import com.example.commons.commons.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public abstract class AbstractIntegrationTest implements RestClient {

  @LocalServerPort
  protected int port;

  @Autowired
  protected NamedParameterJdbcOperations jdbc;

}
