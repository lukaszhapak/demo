package com.example.demo.spring.batch.batch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "batch.basic.entry.job")
public class BasicEntryBatchJobProperties {

  private Integer chunkSize;
  private Integer pageSize;
  private Integer throttleLimit;

}