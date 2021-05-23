package com.testing.model.service.impl;

import com.testing.dao.QuestionDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Question;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionServiceImplTest {
    @InjectMocks
    QuestionServiceImpl questionService;
    @Mock
    DaoFactory daoFactory;
    @Mock
    QuestionDao questionDao;
    Question expected;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        expected = new Question("Which company created java?", "Sun Microsystems",1);
        expected.setId(1);
        when(daoFactory.createQuestionDao()).thenReturn(questionDao);
        when(questionDao.findById(1)).thenReturn(expected);
        when(questionDao.findAll()).thenReturn(Collections.singletonList(expected));
        when(questionDao.getAllQuestionsByTestName("History of Java")).thenReturn(Collections.singletonList(expected));
    }

    @Test
    public void create() {
        Question newQuestion = new Question("Test expected", "Test answer",2);
        questionService.create(newQuestion);
        verify(questionDao,only()).create(newQuestion);
    }

    @Test
    public void findById() {
        Question actual = questionService.findById(1);
        assertEquals(expected,actual);
    }

    @Test
    public void findAll() {
        List<Question> actualList = questionService.findAll();
        assertEquals(expected,actualList.get(0));
    }

    @Test
    public void update() {
        Question testQuestion = new Question("Test question","Test answer",2);
        testQuestion.setId(1);
        questionService.update(testQuestion);
        verify(questionDao,only()).update(testQuestion);
    }

    @Test
    public void delete() {
        questionService.delete(1);
        verify(questionDao,only()).delete(1);
    }

    @Test
    public void getAllQuestionsByTestName() {
        List<Question> actualList = questionService.getAllQuestionsByTestName("History of Java");
        assertEquals(expected,actualList.get(0));
    }
}