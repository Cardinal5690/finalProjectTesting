package com.testing.dao.impl;

import com.testing.model.entity.User;
import com.testing.dao.config.ConnectionPool;
import com.testing.dao.UserDao;
import com.testing.dao.mapper.UserMapper;
import com.testing.dao.util.QueriesResourceManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User create(User entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("user.create"), Statement.RETURN_GENERATED_KEYS);
            setParam(entity, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("User didn't create", e);
        }
        return entity;
    }

    @Override
    public User findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QueriesResourceManager.getProperty("user.find.by.id"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find user", e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueriesResourceManager.getProperty("user.find.all"));
            while (resultSet.next()) {
                allUsers.add(new UserMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any user", e);
        }
        return allUsers;
    }

    @Override
    public void update(User entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("user.update"));
            setParam(entity, preparedStatement);
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.info("User didn't update", e);
        }
    }

    private void setParam(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getSurname());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, String.valueOf(entity.getStatus()));
        preparedStatement.setString(6, String.valueOf(entity.getRole()));
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QueriesResourceManager.getProperty("user.delete"));
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("user didn't delete", e);
        }
    }

    @Override
    public User findUserByLoginAndPassword(String email, String password) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("user.find.by.login.and.password"));
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("User didn't find", e);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("user.find.by.email"));
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("User didn't find", e);
        }
        return null;
    }
}
