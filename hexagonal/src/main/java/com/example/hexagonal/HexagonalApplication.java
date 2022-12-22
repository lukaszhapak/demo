package com.example.hexagonal;

import com.example.hexagonal.configuration.HexagonalConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({HexagonalConfiguration.class})
public class HexagonalApplication {

  public static void main(String[] args) {
	SpringApplication.run(HexagonalApplication.class, args);
  }
}
