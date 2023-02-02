package com.example.demo.commons.config;

import javax.sql.DataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.*;
import net.javacrumbs.shedlock.spring.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "30s")
public class SchedulingConfiguration {

  @Bean
  public LockProvider lockProvider(DataSource dataSource) {
	return new JdbcTemplateLockProvider(dataSource, "shedlock");
  }
}
