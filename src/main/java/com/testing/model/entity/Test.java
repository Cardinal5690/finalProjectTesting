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
    private Integer SubjectId;
    private List<Question> questionList;

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

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Integer getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(Integer subjectId) {
        SubjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) && Objects.equals(testName, test.testName) && Objects.equals(time, test.time) && complexity == test.complexity && Objects.equals(SubjectId, test.SubjectId) && Objects.equals(questionList, test.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, time, complexity, SubjectId, questionList);
    }
}
