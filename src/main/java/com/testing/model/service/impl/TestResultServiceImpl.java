package com.testing.model.service.impl;

import com.testing.dao.TestResultDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Question;
import com.testing.model.entity.Test;
import com.testing.model.entity.TestResult;
import com.testing.model.exception.ResultException;
import com.testing.model.service.TestResultService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestResultServiceImpl implements TestResultService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    TestResultDao testResultDao = daoFactory.createTestResultDao();
    private final static Logger LOGGER = Logger.getLogger(TestResultServiceImpl.class);

    @Override
    public TestResult getTestResultByUserIdAndTestName(int userId, String testName) {
        LOGGER.info("Get optional test result");
        return testResultDao.getTestResultByUserIdAndTestName(userId, testName);
    }

    @Override
    public int calculateResult(int userId, Test test, Map<String, String> testAnswer, List<Question> allQuestionListByTest) {
        TestResult testResult = testResultDao.getTestResultByUserIdAndTestName(userId, test.getTestName());
        int count = 0;
        if (testResult == null) {
            testResult = new TestResult();
            Map<String, String> correctResult = allQuestionListByTest.stream()
                    .collect(Collectors.toMap(Question::getQuestionText, Question::getCorrectAnswer));
            for (Map.Entry<String, String> answer : testAnswer.entrySet()) {
                String resultFromDataBase = correctResult.get(answer.getKey());
                if (resultFromDataBase.equalsIgnoreCase(answer.getValue())) {
                    ++count;
                }
            }
            count = (count * 100) / correctResult.size();
            testResult.setUserId(userId);
            testResult.setTestId(test.getId());
            testResult.setResult(count);
            testResultDao.create(testResult);
            LOGGER.info("CalculateResult");
            return count;
        } else {
            LOGGER.info("throw exception if result exist");
            throw new ResultException("Try to pass test more then once");
        }
    }

    @Override
    public List<TestResult> allTestResultByUserId(int userId) {
        LOGGER.info("return list result");
        return testResultDao.findAllByUserId(userId);
    }

    @Override
    public int getNumberOfRows() {
        LOGGER.info("get number of page");
        return testResultDao.getNumberOfRows();
    }

    @Override
    public List<TestResult> findTestResultPagination(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        LOGGER.info("find pages");
        return testResultDao.findTestResultPagination(start, recordsPerPage);
    }
}

