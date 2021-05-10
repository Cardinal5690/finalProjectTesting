package model.dao.daoImpl;

import model.dao.conectionPool.ConnectionPool;
import model.dao.daoInterface.QuestionDao;
import model.dao.mapper.QuestionMapper;
import model.dao.queriesManager.QueriesResourceManager;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);

    @Override
    public Question create(Question entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("question.create"), Statement.RETURN_GENERATED_KEYS);
            setParam(entity, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.info("Question didn't create", e);
        }
        return entity;
    }

    private void setParam(Question entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getQuestionText());
        preparedStatement.setString(2, entity.getCorrectAnswer());
        preparedStatement.setInt(3, entity.getTest_id());
        preparedStatement.setInt(4, entity.getNumber());
    }

    @Override
    public Question findById(int id) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("question.find.by.id"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new QuestionMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.info("Question didn't find", e);
        }
        return null;
    }

    @Override
    public List<Question> findAll() {
        List<Question> allQuestions = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueriesResourceManager.getProperty("question.find.all"));
            while (resultSet.next()) {
                allQuestions.add(new QuestionMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("Didn't find any question", e);
        }
        return allQuestions;
    }

    @Override
    public void update(Question entity) {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QueriesResourceManager.getProperty("question.update"));
            setParam(entity, preparedStatement);
            preparedStatement.setInt(5,entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Question didn't update",e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = ConnectionPool.getDataSource().getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QueriesResourceManager.getProperty("question.delete"));
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            LOGGER.info("Question didn't delete",e);
        }
    }
}
