package com.testing.model.service.impl;

import com.testing.dao.UserDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Status;
import com.testing.model.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    UserDao userDao = daoFactory.createUserDao();
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User create(User entity) {
        LOGGER.info("The service creates a user");
        return userDao.create(entity);
    }

    @Override
    public User findById(int id) {
        LOGGER.info("The service searches for a user by id");
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Service looks for all users");
        return userDao.findAll();
    }

    @Override
    public void update(User entity) {
        LOGGER.info("The service updates a user");
        userDao.update(entity);
    }

    @Override
    public void delete(int id) {
        LOGGER.info("The service deletes a user");
        userDao.delete(id);
    }

    @Override
    public User findUserByLoginAndPassword(String email, String password) {
        LOGGER.info("The service searches for a user by login and password");
        return userDao.findUserByLoginAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        LOGGER.info("The service searches for a user by email");
        return userDao.findByEmail(email);
    }

    @Override
    public void setNewParameterUser(User user, String userName, String userSurname, String userEmail,String password, String userStatus) {
        LOGGER.info("The  service checks and set new parameter");
        if(!userEmail.isEmpty()){
            user.setEmail(userEmail);
        }
        if(!userName.isEmpty()){
            user.setName(userName);
        }
        if(!userSurname.isEmpty()){
            user.setSurname(userSurname);
        }
        if(!password.isEmpty()){
            user.setPassword(password);
        }
        if(!userStatus.isEmpty() && userStatus.equals("BLOCKED") || userStatus.equals("UNBLOCKED")){
            Status newUserStatus = Enum.valueOf(Status.class, userStatus);
            user.setStatus(newUserStatus);
        }
    }
}
