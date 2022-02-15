package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionTransformer {

  public String transform(String input) {
    List<String> split = List.of(input.split("\n"));
    String question = getQuestion(split);
    Map<String, List<String>> answers = getAnswers(split);
    String result = "" + question + "\n" + "    correct:\n";
    result = addAnswers(answers, result);
    result = result.substring(0, result.length() - 1);
    return result;
  }

  private String addAnswers(Map<String, List<String>> answers, String result) {
    for (String answer : answers.get("correct")) {
      result += answer + "\n";
    }
    result += "    other:\n";
    for (String answer : answers.get("other")) {
      result += answer + "\n";
    }
    return result;
  }

  private List<String> getSolution(List<String> input) {
    String s = input.get(findIndexOfString(input, "solution:"));
    s = s.replace("solution: ", "");
    s = s.replace(",", "");
    s = s.trim();
    return List.of(s.split(" "));
  }

  private String getQuestion(List<String> input) {
    int from = 0;
    int to = findIndexOfString(input, "answers:");

    List<String> lists = (input).subList(from, to);
    String join = String.join("", lists);

    join = join.replace("query", "question");
    join = addQuotesToQuestion(join);

    return join;
  }

  private String addQuotesToQuestion(String input) {
    StringBuilder sb = new StringBuilder(input);
    sb.insert(input.length(), "\"");
    sb.insert(10, "\"");
    return sb.toString();
  }

  private Map<String, List<String>> getAnswers(List<String> input) {
    int from = findIndexOfString(input, "answers:") + 1;
    int to = findIndexOfString(input, "solution:");

    List<String> answers = input.subList(from, to);
    answers = addQuotesToAnswers(answers);
    List<String> solution = getSolution(input);
    return sortAnswers(answers, solution);
  }

  private Map<String, List<String>> sortAnswers(List<String> answers, List<String> solution) {
    List<String> correct = new ArrayList<>();
    List<String> other = new ArrayList<>();
    for (int i = 0; i < answers.size(); i++) {
      boolean isAnswerCorrect = false;
      for (int j = 0; j < solution.size(); j++) {
        if (Character.toString(65 + i).equals(solution.get(j))) {
          correct.add(answers.get(i));
          isAnswerCorrect = true;
          break;
        }
      }
      if (!isAnswerCorrect) {
        other.add(answers.get(i));
      }
    }

    HashMap<String, List<String>> result = new HashMap<>();
    result.put("correct", correct);
    result.put("other", other);
    return result;
  }

  private ArrayList<String> addQuotesToAnswers(List<String> answers) {
    ArrayList<String> result = new ArrayList<>();
    for (String answer : answers) {
      StringBuilder sb = new StringBuilder(answer);
      sb.insert(answer.length(), "\"");
      sb.insert(8, "\"");
      result.add(sb.toString());
    }
    return result;
  }

  private int findIndexOfString(List<String> input, String wanted) {
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i).contains(wanted)) {
        return i;
      }
    }
    return 0;
  }
}
