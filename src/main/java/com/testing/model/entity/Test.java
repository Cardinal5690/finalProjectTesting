package com.testing.model.entity;

import com.testing.model.entity.type.Complexity;
import com.testing.model.validation.constraint.ComplexitySubset;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class Test {
    @NotNull
    private Integer id;
    @NotNull
    @Size(max = 50)
    private String testName;
    @NotNull
    @Min(value = 15)
    @Max(120)
    private Integer time;
    @ComplexitySubset(anyOf = {Complexity.EASY,Complexity.DIFFICULT,Complexity.MIDDLE})
    private Complexity complexity;
    private List<Question> questionList;
    @NotNull
    private Integer subject_id;

    public Test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) && Objects.equals(testName, test.testName) && Objects.equals(time, test.time) && complexity == test.complexity && Objects.equals(questionList, test.questionList) && Objects.equals(subject_id, test.subject_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, time, complexity, questionList, subject_id);
    }
}
