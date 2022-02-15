package com.example.demo;

import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;


@Slf4j
public class MuleTest {
    TestRestTemplate rest = new TestRestTemplate();
    String bikerRiders = "http://localhost:8081";
    String people = "http://localhost:8082";
    String tournaments = "http://localhost:8083";

    @Test
    void bikeRidersPerson() {
        ResponseEntity<String> mule = rest
                .getForEntity(bikerRiders + "/person/75062506701", String.class);
    }

    @Test
    void peoplePerson() {
        ResponseEntity<String> mule = rest
                .withBasicAuth("username", "password")
                .getForEntity(people + "/person?pesel=85062606702", String.class);
    }

    @Test
    void tokenTournaments() {
        ResponseEntity<String> mule = rest
                .withBasicAuth("username", "password")
                .postForEntity(tournaments + "/auth", null, String.class);
    }
}
