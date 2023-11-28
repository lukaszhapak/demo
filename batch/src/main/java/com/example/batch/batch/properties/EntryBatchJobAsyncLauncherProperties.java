package com.example.batch.batch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "batch.entry.job.async.launcher")
public class EntryBatchJobAsyncLauncherProperties {

    private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer queueCapacity;
	private Integer keepAliveSeconds;
	private Boolean allowCoreThreadTimeout;

}