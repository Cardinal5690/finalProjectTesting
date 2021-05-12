package com.testing.dao;

import com.testing.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findUserByLoginAndPassword(String email, int password);
}
