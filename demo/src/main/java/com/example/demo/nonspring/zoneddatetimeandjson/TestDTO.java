package com.example.demo.nonspring.zoneddatetimeandjson;

import lombok.*;

import java.time.ZonedDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class TestDTO {
    ZonedDateTime zonedDateTime;
}
