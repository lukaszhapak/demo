package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TransformQuestionTest {

  private final QuestionTransformer questionTransformer = new QuestionTransformer();


//  @Test
//  void transform() {
//    // given
//    String input =
//        "query: Which of these are considered idempotent REST operations? Select all that apply.\n"
//            + "    answers:\n"
//            + "      - POST\n"
//            + "      - PUT\n"
//            + "      - GET\n"
//            + "      - DELETE\n"
//            + "    solution: B, C, D";
//    String expected =
//        "question: \"Which of these are considered idempotent REST operations? Select all that apply.\"\n"
//            + "    correct:\n"
//            + "      - \"PUT\"\n"
//            + "      - \"GET\"\n"
//            + "      - \"DELETE\"\n"
//            + "    other:\n"
//            + "      - \"POST\"";
//
//    // when
//    String actual = questionTransformer.transform(input);
//
//    //then
//    assertEquals(expected, actual);
//  }

  @Test
  void tezd(){
    // given
    SourceQuestion sourceQuestion = new SourceQuestion();
    sourceQuestion.setQuery("Which of these are considered idempotent REST operations? Select all that apply.");
    sourceQuestion.setAnswers(List.of(
        "POST",
        "PUT",
        "GET",
        "DELETE"
    ));
    sourceQuestion.setSolution("B, C, D");

    MappedQuestion expected = new MappedQuestion();
    expected.setQuestion("Which of these are considered idempotent REST operations? Select all that apply.");
    expected.setCorrect(List.of(
        "PUT",
        "GET",
        "DELETE"
    ));
    expected.setOther(List.of(
        "POST"
    ));

    // when
    MappedQuestion actual = questionTransformer.transform(sourceQuestion);

    // then
    assertEquals(expected.getQuestion(), actual.getQuestion());
    assertEquals(expected.getCorrect().get(0), actual.getCorrect().get(0));
    assertEquals(expected.getCorrect().get(1), actual.getCorrect().get(1));
    assertEquals(expected.getCorrect().get(2), actual.getCorrect().get(2));
    assertEquals(expected.getOther().get(0), actual.getOther().get(0));
  }
}
