package com.example.batch;

import com.example.batch.batch.config.EntryAutomaticRetryBatchJobConfig;
import com.example.batch.batch.config.EntryBatchJobConfig;
import com.example.batch.batch.config.EntryManualRetryBatchJobConfig;
import com.example.batch.batch.config.GlobalBatchJobConfig;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
@Import({EntryBatchJobConfig.class, GlobalBatchJobConfig.class, EntryManualRetryBatchJobConfig.class, EntryAutomaticRetryBatchJobConfig.class})
public class BatchApplication {

  public static void main(String[] args) {
	SpringApplication.run(BatchApplication.class, args);
  }

}
