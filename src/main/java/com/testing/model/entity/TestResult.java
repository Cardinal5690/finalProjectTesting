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
    private Integer user_id;
    @NotNull
    private Integer test_id;

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
        TestResult that = (TestResult) o;
        return Objects.equals(id, that.id) && Objects.equals(result, that.result) && Objects.equals(user_id, that.user_id) && Objects.equals(test_id, that.test_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result, user_id, test_id);
    }
}
