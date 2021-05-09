package model.entity;

import model.entity.type.Complexity;

import java.util.Objects;

public class Test {
    private Integer id;
    private String testName;
    private Integer time;
    private Complexity complexity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) && Objects.equals(testName, test.testName) && Objects.equals(time, test.time) && complexity == test.complexity && Objects.equals(subject_id, test.subject_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, time, complexity, subject_id);
    }
}
