package com.testing.model.service.impl;

import com.testing.dao.UserDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Role;
import com.testing.model.entity.type.Status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("Bill", "Wilson","wilson@gmail.com","123456", Role.STUDENT, Status.UNBLOCKED);
        user.setId(1);
        when(daoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findById(1)).thenReturn(user);
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
        when(userDao.findUserByLoginAndPassword("wilson@gmail.com","123456")).thenReturn(user);
    }

    @Test
    public void create() {
        User testUser = new User();
        testUser.setId(2);
        userService.create(testUser);
        verify(userDao,times(1)).create(testUser);
    }

    @Test
    public void findById() {
        User actual = userService.findById(1);
        assertEquals(user,actual);
    }

    @Test
    public void findAll() {
        List<User> actual = userService.findAll();
        assertEquals(user,actual.get(0));
    }

    @Test
    public void update() {
        User test = new User();
        test.setId(2);
        userService.update(test);
        verify(userDao,times(1)).update(test);
    }

    @Test
    public void delete() {
        userService.delete(2);
        verify(userDao,times(1)).delete(2);
    }

    @Test
    public void findUserByLoginAndPassword() {
        User actual = userService.findUserByLoginAndPassword("wilson@gmail.com","123456");
        assertEquals(user,actual);
    }
}