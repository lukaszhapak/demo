package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MovieConfiguration.class})
public class HexagonalConfiguration {

}
