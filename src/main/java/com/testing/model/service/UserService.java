package com.testing.model.service;

import com.testing.model.entity.User;

public interface UserService extends GeneralService<User> {
    User findUserByLoginAndPassword(String email, String password);
    User findByEmail(String email);
    void setNewParameterUser (User user, String userName, String userSurname, String userEmail,String password, String userStatus);
}
