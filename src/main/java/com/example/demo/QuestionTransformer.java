package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionTransformer {

  public MappedQuestion transform(SourceQuestion sourceQuestion) {
    MappedQuestion result = new MappedQuestion();
    result.setQuestion(sourceQuestion.getQuery());
    Map<String, List<String>> answers = getAnswers(
        sourceQuestion.getAnswers(),
        sourceQuestion.getSolution());
    result.setCorrect(answers.get("correct"));
    result.setOther(answers.get("other"));
    return result;
  }

  private Map<String, List<String>> getAnswers(List<String> answers, String solution) {
    List<String> solutionAsList = getSolutionAsList(solution);
    return sortAnswers(answers, solutionAsList);
  }

  private List<String> getSolutionAsList(String solution) {
    String result = solution.replace(",", "");
    result = result.trim();
    return List.of(result.split(" "));
  }

  private Map<String, List<String>> sortAnswers(List<String> answers, List<String> solution) {
    List<String> correct = new ArrayList<>();
    List<String> other = new ArrayList<>();
    addAnswersToLists(answers, solution, correct, other);
    return getAnswersMap(correct, other);
  }

  private void addAnswersToLists(List<String> answers, List<String> solution, List<String> correct,
      List<String> other) {
    for (int i = 0; i < answers.size(); i++) {
      boolean isAnswerCorrect = false;
      for (String it : solution) {
        if (Character.toString(65 + i).equals(it)) {
          correct.add(answers.get(i));
          isAnswerCorrect = true;
          break;
        }
      }
      if (!isAnswerCorrect) {
        other.add(answers.get(i));
      }
    }
  }

  private HashMap<String, List<String>> getAnswersMap(List<String> correct, List<String> other) {
    HashMap<String, List<String>> result = new HashMap<>();
    result.put("correct", correct);
    result.put("other", other);
    return result;
  }
}
