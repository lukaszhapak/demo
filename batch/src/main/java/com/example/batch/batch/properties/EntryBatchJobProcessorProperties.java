package com.example.batch.batch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "batch.entry.job.processor")
public class EntryBatchJobProcessorProperties {

    private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer queueCapacity;

}