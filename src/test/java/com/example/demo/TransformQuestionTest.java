package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransformQuestionTest {

  private final QuestionTransformer questionTransformer = new QuestionTransformer();

  @Test
  void transform() {
    // given
    String input = "query: Which of these are considered idempotent REST operations? Select all that apply.\n"
        + "    answers:\n"
        + "      - POST\n"
        + "      - PUT\n"
        + "      - GET\n"
        + "      - DELETE\n"
        + "    solution: B, C, D";
    String expected = "question: \"Which of these are considered idempotent REST operations? Select all that apply.\"\n"
        + "    correct:\n"
        + "      - \"PUT\"\n"
        + "      - \"GET\"\n"
        + "      - \"DELETE\"\n"
        + "    other:\n"
        + "      - \"POST\"";

    // when
    String actual = questionTransformer.transform(input);

    //then
    Assertions.assertEquals(expected, actual);
  }
}
