package model.dao.daoImpl;

import model.dao.conectionPool.ConnectionPool;
import model.dao.daoInterface.SubjectDao;
import model.dao.mapper.SubjectMapper;
import model.dao.queriesManager.QueriesResourceManager;
import model.entity.Subject;
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
            preparedStatement.setInt(2, entity.getId());
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
}
