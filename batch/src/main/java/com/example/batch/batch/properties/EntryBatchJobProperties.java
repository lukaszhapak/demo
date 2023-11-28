package com.example.batch.batch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "batch.entry.job")
public class EntryBatchJobProperties {

  private Integer chunkSize;
  private Integer pageSize;
  private Integer throttleLimit;

}