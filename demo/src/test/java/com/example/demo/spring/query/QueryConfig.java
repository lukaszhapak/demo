package com.example.demo.spring.query;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@TestConfiguration
@EnableJpaRepositories
@AutoConfigureDataJpa
class QueryConfig {

}
