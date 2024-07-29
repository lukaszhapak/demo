package com.example.demo.spring.batch.config


import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.task.SyncTaskExecutor
import org.springframework.core.task.TaskExecutor

@TestConfiguration
class BatchSpecConfig {

    @Bean
    TaskExecutor asyncJobLauncherTaskExecutor() {
        return new SyncTaskExecutor();
    }
}
