package com.testing.model.service.impl;

import com.testing.dao.TestResultDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Question;
import com.testing.model.entity.TestResult;
import com.testing.model.exception.ResultException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestResultServiceImplTest {
    @InjectMocks
    TestResultServiceImpl testResultService;
    @Mock
    DaoFactory daoFactory;
    @Mock
    TestResultDao testResultDao;

    TestResult expected;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expected = new TestResult(50, 1, 1);
        expected.setId(1);
        when(daoFactory.createTestResultDao()).thenReturn(testResultDao);
        when(testResultDao.getTestResultByUserIdAndTestName(1, "History")).thenReturn(expected);
        when(testResultDao.getNumberOfRows()).thenReturn(5);
        when(testResultDao.findAllByUserId(1)).thenReturn(Collections.singletonList(expected));
        when(testResultDao.findTestResultPagination(0, 5)).thenReturn(Collections.singletonList(expected));
    }

    @Test
    public void getTestResultByUserIdAndTestName() {
        TestResult actual = testResultService.getTestResultByUserIdAndTestName(1, "History");
        assertEquals(expected, actual);
    }

    @Test(expected = ResultException.class)
    public void testThrowExceptionWhenResultExist() {
        com.testing.model.entity.Test test = new com.testing.model.entity.Test();
        test.setTestName("History");
        testResultService.calculateResult(1, test, null, null);
    }

    @Test
    public void testCalculateResultWhenResultIsNotExist() {
        //before
        Question firstCorrectQuestion = new Question("1+1=", "2", 1);
        Question secondCorrectQuestion = new Question("Through what country did Lady Godiva ride?", "England", 1);
        Question thirdCorrectQuestion = new Question("What country was Vasco da Gama from?", "Portugal", 1);
        Question fourthCorrectQuestion = new Question("3+3=", "6", 1);

        List<Question> correctQuestions = Arrays.asList(firstCorrectQuestion, secondCorrectQuestion, thirdCorrectQuestion, fourthCorrectQuestion);

        Map<String,String> answersFromUser = new HashMap<>();
        answersFromUser.put(firstCorrectQuestion.getQuestionText(),firstCorrectQuestion.getCorrectAnswer());
        answersFromUser.put(secondCorrectQuestion.getQuestionText(),"EnGLanD");
        answersFromUser.put(thirdCorrectQuestion.getQuestionText(),"Wrong");
        answersFromUser.put(fourthCorrectQuestion.getQuestionText(), fourthCorrectQuestion.getCorrectAnswer());
        com.testing.model.entity.Test test = new com.testing.model.entity.Test();
        test.setTestName("History");
        test.setId(1);
        //then
        Integer actual = testResultService.calculateResult(2,test,answersFromUser,correctQuestions);
        //expected
        Integer expectedInt = 75;

        assertEquals(expectedInt,actual);
    }

    @Test
    public void allTestResultByUserId() {
        List<TestResult> actual = testResultService.allTestResultByUserId(1);
        assertEquals(expected, actual.get(0));
    }

    @Test
    public void getNumberOfRows() {
        Integer actual = testResultService.getNumberOfRows();

        Integer expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    public void findTestResultPagination() {
        List<TestResult> actualList = testResultService.findTestResultPagination(1, 5);
        assertEquals(expected, actualList.get(0));
    }

}