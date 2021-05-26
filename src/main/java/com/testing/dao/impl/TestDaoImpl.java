package com.testing.dao.impl;

import com.testing.dao.mapper.TestMapper;
import com.testing.dao.util.QueriesResourceManager;
import com.testing.model.entity.Test;
import com.testing.dao.config.ConnectionPool;
import com.testing.dao.TestDao;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoImpl implements TestDao {
    private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

    @Override
    public Test create(Test entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.create"), Statement.RETURN_GENERATED_KEYS)) {
            setParam(entity, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("Test didn't create", e);
        }
        return entity;
    }

    @Override
    public Test findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.find.by.id"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new TestMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Test didn't find", e);
        }
        return null;
    }

    @Override
    public List<Test> findAll() {
        List<Test> allTests = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QueriesResourceManager.getProperty("test.find.all"));
            while (resultSet.next()) {
                allTests.add(new TestMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any tests", e);
        }
        return allTests;
    }

    @Override
    public void update(Test entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.update"))) {
            setParam(entity, preparedStatement);
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Test didn't update", e);
        }
    }

    private void setParam(Test entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getTestName());
        preparedStatement.setString(2, String.valueOf(entity.getComplexity()));
        preparedStatement.setInt(3, entity.getTime());
        preparedStatement.setInt(4,entity.getSubjectId());
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QueriesResourceManager.getProperty("test.delete"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.info("Test didn't delete");
        }
    }

    @Override
    public List<Test> getAllTestBySubjectId(int subjectId) {
        List<Test> allTests = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.find.all.by.subject.id"))) {
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allTests.add(new TestMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any tests", e);
        }
        return allTests;
    }

    @Override
    public List<Test> getBySubjectTitle(String title) {
        List<Test> allTests = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.find.by.subject.name"))) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allTests.add(new TestMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any test", e);
        }
        return allTests;
    }

    @Override
    public Test getByName(String name) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.find.name"))) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            return new TestMapper().extractFromResultSet(resultSet);
            }
        }catch (SQLException e) {
            LOGGER.info("Didn't find any test", e);
        }
        return null;
    }
}
