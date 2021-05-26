package com.testing.model.entity;

import com.testing.model.entity.type.Complexity;
import com.testing.model.validation.constraint.ComplexitySubset;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Test {
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
    @NotNull
    private Integer subjectId;

    public Test() {
    }

    public Test(String testName, Integer time, Complexity complexity, Integer subjectId) {
        this.testName = testName;
        this.time = time;
        this.complexity = complexity;
        this.subjectId = subjectId;
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) && Objects.equals(testName, test.testName) && Objects.equals(time, test.time) && complexity == test.complexity && Objects.equals(subjectId, test.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, time, complexity, subjectId);
    }
}
