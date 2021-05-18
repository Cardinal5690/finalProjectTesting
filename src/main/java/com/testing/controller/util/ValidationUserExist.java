package com.testing.controller.util;

import com.testing.model.service.UserService;
import com.testing.model.service.impl.UserServiceImpl;

public class ValidationUserExist {

    public boolean userExist(String email) {
        UserService userService = new UserServiceImpl();
        return userService.findByEmail(email) == null;
    }
}
