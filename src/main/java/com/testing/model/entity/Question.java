package com.testing.model.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Question {
    @NotNull
    private Integer id;
    @NotNull
    @Min(1)
    private Integer number;
    @NotNull
    @Size(max = 500)
    private String questionText;
    @NotNull
    private String correctAnswer;
    private Integer test_id;
    private List<Answer> answerList;

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(number, question.number) && Objects.equals(questionText, question.questionText) && Objects.equals(correctAnswer, question.correctAnswer) && Objects.equals(test_id, question.test_id) && Objects.equals(answerList, question.answerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, questionText, correctAnswer, test_id, answerList);
    }
}
