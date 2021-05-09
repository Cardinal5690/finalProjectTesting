package model.entity;

import java.util.Objects;

public class Question {
    private Integer id;
    private Integer number;
    private String questionText;
    private String correctAnswer;
    private Integer test_id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(number, question.number) && Objects.equals(questionText, question.questionText) && Objects.equals(correctAnswer, question.correctAnswer) && Objects.equals(test_id, question.test_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, questionText, correctAnswer, test_id);
    }
}
