package com.testing.model.entity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Answer {
    @NotNull
    private Integer id;
    @NotNull
    private String letter;
    @NotNull
    private String answerText;
    private int question_id;

    public Answer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return question_id == answer.question_id && Objects.equals(id, answer.id) && Objects.equals(letter, answer.letter) && Objects.equals(answerText, answer.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, letter, answerText, question_id);
    }
}
