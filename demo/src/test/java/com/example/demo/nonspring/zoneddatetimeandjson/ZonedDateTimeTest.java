package com.example.demo.nonspring.zoneddatetimeandjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class ZonedDateTimeTest {

    final static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void testName() throws JsonProcessingException {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        TestDTO testDTO = new TestDTO(zonedDateTime);
        String testDTOasJson = objectMapper.writeValueAsString(testDTO);
        TestDTO testDTOAgainAsObject = objectMapper.readValue(testDTOasJson, TestDTO.class);
    }
}
