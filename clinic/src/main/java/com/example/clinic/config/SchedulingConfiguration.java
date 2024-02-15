package com.example.clinic.config;

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
class SchedulingConfiguration {

  @Bean
  LockProvider lockProvider(DataSource dataSource) {
	return new JdbcTemplateLockProvider(dataSource, "shedlock");
  }
}
