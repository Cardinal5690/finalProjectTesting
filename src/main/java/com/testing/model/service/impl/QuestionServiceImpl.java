package com.testing.model.service.impl;

import com.testing.dao.QuestionDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Question;
import com.testing.model.service.QuestionService;
import org.apache.log4j.Logger;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    QuestionDao questionDao = daoFactory.createQuestionDao();
    private static final Logger LOGGER = Logger.getLogger(QuestionServiceImpl.class);

    @Override
    public Question create(Question entity) {
        LOGGER.info("The service creates a question");
        return questionDao.create(entity);
    }

    @Override
    public Question findById(int id) {
        LOGGER.info("The service searches for a question by id");
        return questionDao.findById(id);
    }

    @Override
    public List<Question> findAll() {
        LOGGER.info("The service looks for all questions");
        return questionDao.findAll();
    }

    @Override
    public void update(Question entity) {
        LOGGER.info("The service updates a question");
        questionDao.update(entity);
    }

    @Override
    public void delete(int id) {
        LOGGER.info("The service deletes a question");
        questionDao.delete(id);
    }

    @Override
    public List<Question> getAllQuestionsByTestName(String testName) {
        LOGGER.info("The service looks for all questions");
        return questionDao.getAllQuestionsByTestName(testName);
    }
}
