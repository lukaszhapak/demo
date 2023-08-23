package com.example.batch.config;

import com.example.batch.resource.MockEntryResource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@TestConfiguration
public class TestConfig {

  @Bean
  public MockEntryResource mockEntryResource() {
	return Mockito.mock(MockEntryResource.class);
  }

  @Bean
  public TaskExecutor asyncJobLauncherTaskExecutor() {
	return new SyncTaskExecutor();
  }
}
