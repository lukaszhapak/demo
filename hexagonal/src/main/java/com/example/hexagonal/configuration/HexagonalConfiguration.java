package com.example.hexagonal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Import({MovieConfiguration.class})
public class HexagonalConfiguration {

}
