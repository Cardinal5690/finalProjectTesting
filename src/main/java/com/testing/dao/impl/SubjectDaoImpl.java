package com.testing.dao.impl;

import com.testing.dao.SubjectDao;
import com.testing.dao.mapper.SubjectMapper;
import com.testing.dao.util.QueriesResourceManager;
import com.testing.model.entity.Subject;
import com.testing.dao.config.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger LOGGER = Logger.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject create(Subject entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.create"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("Subject didn't create", e);
        }
        return entity;
    }

    @Override
    public Subject findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.find.by.id"));
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new SubjectMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find subject", e);
        }
        return null;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> allSubjects = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueriesResourceManager.getProperty("subject.find.all"));
            while (resultSet.next()) {
                allSubjects.add(new SubjectMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any subject", e);
        }
        return allSubjects;
    }

    @Override
    public void update(Subject entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.update"));
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Subject didn't update", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.delete"));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.info("Subject didn't delete");
        }
    }

    @Override
    public List<Subject> findAllByUserId(int userId) {
        ArrayList<Subject> subjects =new ArrayList<>();
        try(Connection connection = ConnectionPool.getDataSource().getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.find.all.by.user.id"));
            preparedStatement.setInt(1,userId);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                subjects.add(new SubjectMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e){
            LOGGER.info("Didn't find any subject");
        }
        return subjects;
    }

    @Override
    public List<Subject> findAllByLocale(String locale) {
        ArrayList<Subject> subjects =new ArrayList<>();
        try(Connection connection = ConnectionPool.getDataSource().getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("subject.find.all.by.locale"));
            preparedStatement.setString(1,locale.toUpperCase());
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                subjects.add(new SubjectMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e){
            LOGGER.info("Didn't find any subject");
        }
        return subjects;
    }
}
