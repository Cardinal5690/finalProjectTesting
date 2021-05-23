package com.testing.model.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Question {
    private Integer id;
    @NotNull
    @Size(max = 500)
    private String questionText;
    @NotNull
    private String correctAnswer;
    @NotNull
    private Integer testId;

    public Question() {
    }

    public Question(String questionText, String correctAnswer, Integer testId) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.testId = testId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id)  && Objects.equals(questionText, question.questionText) && Objects.equals(correctAnswer, question.correctAnswer) && Objects.equals(testId, question.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, correctAnswer, testId);
    }
}
