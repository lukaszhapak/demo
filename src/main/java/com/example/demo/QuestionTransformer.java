package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionTransformer {

  public String transform(String input) {
    String[] split = input.split("\n");
    String question = getQuestion(split);
    Map<String, List<String>> answers = getAnswers(split);
    String result = "" + question + "\n" + "    correct:\n";
    for (String answer: answers.get("correct")){
      result += answer + "\n";
    }
    result += "    other:\n";
    for (String answer: answers.get("other")){
      result += answer + "\n";
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  private String[] getSolution(String[] input) {
    String s = input[findIndexOfString(input, "solution:")];
    s = s.replace("solution: ", "");
    s = s.replace(",", "");
    s = s.trim();
    return s.split(" ");
  }

  private String getQuestion(String[] input) {
    int from = 0;
    int to = findIndexOfString(input, "answers:");

    String join = String.join("", new ArrayList<>(Arrays.asList(input)
        .subList(from, to)));

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

  private Map<String, List<String>> getAnswers(String[] input) {
    int from = findIndexOfString(input, "answers:") + 1;
    int to = findIndexOfString(input, "solution:");

    ArrayList<String> answers = new ArrayList<>(Arrays.asList(input)
        .subList(from, to));
    answers = addQuotesToAnswers(answers);
    String[] solution = getSolution(input);
    return sortAnswers(answers, solution);
  }

  private Map<String, List<String>> sortAnswers(ArrayList<String> answers, String[] solution) {
    List<String> correct = new ArrayList<>();
    List<String> other = new ArrayList<>();
    for (int i = 0; i < answers.size(); i++) {
      boolean isAnswerCorrect = false;
      for (int j = 0; j < solution.length; j++) {
        if (Character.toString(65 + i).equals(solution[j])) {
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

  private ArrayList<String> addQuotesToAnswers(ArrayList<String> answers) {
    ArrayList<String> result = new ArrayList<>();
    for (String answer : answers) {
      StringBuilder sb = new StringBuilder(answer);
      sb.insert(answer.length(), "\"");
      sb.insert(8, "\"");
      result.add(sb.toString());
    }
    return result;
  }

  private int findIndexOfString(String[] input, String wanted) {
    for (int i = 0; i < input.length; i++) {
      if (input[i].contains(wanted)) {
        return i;
      }
    }
    return 0;
  }
}
