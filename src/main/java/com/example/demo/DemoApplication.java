package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.*;

@EnableCaching
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
