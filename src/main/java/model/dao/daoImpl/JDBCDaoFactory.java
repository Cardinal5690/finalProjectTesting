package model.dao.daoImpl;

import model.dao.conectionPool.ConnectionPool;
import model.dao.daoInterface.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(JDBCDaoFactory.class);
    DataSource dataSource = ConnectionPool.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public TestDao createTestDao() {
        return new TestDaoImpl();
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new SubjectDaoImpl();
    }

    @Override
    public ResultTestDao createResultTestDao() {
        return new ResultTestImpl();
    }

    @Override
    public QuestionDao createQuestionDao() {
        return new QuestionDaoImpl();
    }

    @Override
    public AnswerDao createAnswerDao() {
        return new AnswerDaoImpl();
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Failed establishing connection to database", e);
            throw new RuntimeException(e);
        }
    }
}
