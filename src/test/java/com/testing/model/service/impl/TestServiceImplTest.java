package com.testing.model.service.impl;

import com.testing.dao.TestDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.type.Complexity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestServiceImplTest {
    @InjectMocks
    TestServiceImpl testService;
    @Mock
    DaoFactory daoFactory;
    @Mock
    TestDao testDao;

     com.testing.model.entity.Test expected;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        expected = new com.testing.model.entity.Test();
        expected.setTime(30);
        expected.setId(1);
        expected.setComplexity(Complexity.MIDDLE);
        when(daoFactory.createTestDao()).thenReturn(testDao);
        when(testDao.findById(1)).thenReturn(expected);
        when(testDao.findAll()).thenReturn(Collections.singletonList(expected));
    }

    @Test
    public void create() {
        com.testing.model.entity.Test test = new com.testing.model.entity.Test();
        test.setId(2);
        testService.create(test);
        verify(testDao,times(1)).create(test);
    }

    @Test
    public void findById() {
        com.testing.model.entity.Test actual = testService.findById(1);
        assertEquals(expected,actual);
    }

    @Test
    public void findAll() {
        List<com.testing.model.entity.Test> actual = testService.findAll();
        assertEquals(expected,actual.get(0));
    }

    @Test
    public void update() {
        com.testing.model.entity.Test actual = new com.testing.model.entity.Test();
        actual.setId(1);
        actual.setComplexity(Complexity.EASY);
        testService.update(actual);
        verify(testDao,times(1)).update(actual);
    }

    @Test
    public void delete() {
        testService.delete(2);
        verify(testDao,times(1)).delete(2);
    }
}