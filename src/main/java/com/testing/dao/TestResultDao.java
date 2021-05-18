package com.testing.dao;

import com.testing.model.entity.TestResult;

import java.util.List;

public interface TestResultDao {
    TestResult getTestResultByUserIdAndTestName (int userId, String testName);
    TestResult create(TestResult testResult);
    List<TestResult> findAllByUserId (int userId);
}
