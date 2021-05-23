package com.testing.model.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TestResult {
    @NotNull
    private Integer id;
    @Min(0)
    @Max(100)
    private Integer result;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer testId;

    public TestResult() {
    }

    public TestResult(Integer result, Integer userId, Integer testId) {
        this.result = result;
        this.userId = userId;
        this.testId = testId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        TestResult that = (TestResult) o;
        return Objects.equals(id, that.id) && Objects.equals(result, that.result) && Objects.equals(userId, that.userId) && Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result, userId, testId);
    }
}
