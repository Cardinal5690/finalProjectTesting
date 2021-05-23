package com.testing.dao.impl;

import com.testing.dao.TestResultDao;
import com.testing.dao.config.ConnectionPool;
import com.testing.dao.mapper.TestResultMapper;
import com.testing.dao.util.QueriesResourceManager;
import com.testing.model.entity.TestResult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestResultDaoImpl implements TestResultDao {
    private static final Logger LOGGER = Logger.getLogger(TestResultDaoImpl.class);

    @Override
    public TestResult getTestResultByUserIdAndTestName(int userId, String testName) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.result.find.by.name.and.test.name"));
            preparedStatement.setString(1, testName);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new TestResultMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Get test by user id and test name", e);
        }
        return null;
    }

    @Override
    public TestResult create(TestResult testResult) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.result.create"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, testResult.getResult());
            preparedStatement.setInt(2, testResult.getUserId());
            preparedStatement.setInt(3, testResult.getTestId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                testResult.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("Result didn't create", e);
        }
        return testResult;
    }

    @Override
    public List<TestResult> findAllByUserId(int userId) {
        List<TestResult> testResults = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("test.result.find.all.by.user.id"));
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                testResults.add(new TestResultMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any tests", e);
        }
        return testResults;
    }

    @Override
    public int getNumberOfRows() {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()){
             PreparedStatement statement = connection.prepareStatement(QueriesResourceManager.getProperty("test.result.get.number"));
            int numOfRows = 0;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numOfRows = resultSet.getInt(1);
            }
            return numOfRows;
        } catch (SQLException e) {
            LOGGER.info("Cannot find number of rows", e);
        }
        return 0;
    }

    @Override
    public List<TestResult> findTestResultPagination(int start, int recordsPerPage) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()){
             PreparedStatement statement =
                     connection.prepareStatement(QueriesResourceManager.getProperty("test.result.find.pagination"));
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, start);
            ResultSet resultSet = statement.executeQuery();
            List<TestResult> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(new TestResultMapper().extractFromResultSet(resultSet));
            }
            return results;
        } catch (SQLException e) {
            LOGGER.info("Cannot find results on page", e);
            return null;
        }
    }
}

