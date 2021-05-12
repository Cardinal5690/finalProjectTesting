package com.testing.dao.impl;

import com.testing.dao.util.QueriesResourceManager;
import com.testing.dao.config.ConnectionPool;
import com.testing.dao.AnswerDao;
import com.testing.dao.mapper.AnswerMapper;
import com.testing.model.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private static final Logger LOGGER = Logger.getLogger(AnswerDaoImpl.class);

    @Override
    public Answer create(Answer entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("answer.create"), Statement.RETURN_GENERATED_KEYS);
            setParam(entity, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("Answer didn't create", e);
        }
        return entity;
    }

    private void setParam(Answer entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getAnswerText());
        preparedStatement.setInt(2, entity.getQuestion_id());
        preparedStatement.setString(3, entity.getLetter());
    }

    @Override
    public Answer findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("answer.find.by.id"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new AnswerMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Answer didn't find", e);
        }
        return null;
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> allAnswers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueriesResourceManager.getProperty("answer.find.all"));
            while (resultSet.next()) {
                allAnswers.add(new AnswerMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any answer", e);
        }
        return allAnswers;
    }

    @Override
    public void update(Answer entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("answer.update"));
            setParam(entity, preparedStatement);
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Answer didn't update", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("answer.delete"));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.info("Answer didn't delete", e);
        }
    }
}
