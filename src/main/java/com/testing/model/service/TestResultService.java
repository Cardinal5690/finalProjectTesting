package com.testing.model.service;

import com.testing.model.entity.Question;
import com.testing.model.entity.Test;
import com.testing.model.entity.TestResult;

import java.util.List;
import java.util.Map;

public interface TestResultService {
    TestResult getTestResultByUserIdAndTestName (int userId, String testName);
    int calculateResult(int userId, Test test, Map<String, String> testAnswer, List<Question> allQuestionListByTest);
    List<TestResult> allTestResultByUserId (int userId);
    int getNumberOfRows();
    List<TestResult> findTestResultPagination(int currentPage, int recordsPerPage);
}
