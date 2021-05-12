package com.testing.model.entity;

import java.util.Objects;

public class TestResult {
    private Integer id;
    private Integer result;
    private Integer user_id;
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
