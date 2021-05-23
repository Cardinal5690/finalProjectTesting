package com.testing.model.service.impl;

import com.testing.dao.SubjectDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Subject;
import com.testing.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SubjectServiceImplTest {
    @InjectMocks
    SubjectServiceImpl  service;
    @Mock
    DaoFactory daoFactory;
    @Mock
    SubjectDao subjectDao;
    Subject subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new Subject();
        subject.setId(1);
        subject.setTitle("English");
        when(daoFactory.createSubjectDao()).thenReturn(subjectDao);
        when(subjectDao.findById(1)).thenReturn(subject);
        when(subjectDao.findAll()).thenReturn(Collections.singletonList(subject));
        when(subjectDao.findAllByLocale("EN")).thenReturn(Collections.singletonList(subject));
    }

    @Test
    public void create() {
        Subject testSubject = new Subject();
        testSubject.setId(2);
        service.create(testSubject);
        verify(subjectDao, times(1)).create(testSubject);
    }

    @Test
    public void findById() {
        Subject actual = service.findById(1);
        assertEquals(subject,actual);
    }

    @Test
    public void findAll() {
        List<Subject> actual = service.findAll();
        assertEquals(subject, actual.get(0));
    }

    @Test
    public void update() {
        Subject actual = new Subject();
        actual.setId(1);
        actual.setTitle("History");
        service.update(actual);
        verify(subjectDao, times(1)).update(actual);
    }

    @Test
    public void delete() {
        service.delete(2);
        verify(subjectDao,times(1)).delete(2);
    }

    @Test
    public void testFindAllSubjectsByLocale(){
        List<Subject> actualList= service.findAllByLocale("EN");
        assertEquals(subject,actualList.get(0));
    }
}