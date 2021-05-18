package com.testing.model.service.impl;

import com.testing.dao.AnswerDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Answer;
import com.testing.model.entity.Question;
import com.testing.model.service.AnswerService;
import org.apache.log4j.Logger;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    AnswerDao answerDao = daoFactory.createAnswerDao();
    private static final Logger LOGGER = Logger.getLogger(AnswerServiceImpl.class);

    @Override
    public void setAnswer(Question question) {
        LOGGER.info("The service sets all the answers for the question");
        question.setAnswerList(answerDao.findAllByQuestionId(question.getId()));
    }

    @Override
    public Answer create(Answer entity) {
        LOGGER.info("The service creates an answer");
        return answerDao.create(entity);
    }

    @Override
    public Answer findById(int id) {
        LOGGER.info("The service searches for an answer by id");
        return answerDao.findById(id);
    }

    @Override
    public List<Answer> findAll() {
        LOGGER.info("The service looks for all answers");
        return answerDao.findAll();
    }

    @Override
    public void update(Answer entity) {
        LOGGER.info("The service updates an answer");
        answerDao.update(entity);
    }

    @Override
    public void delete(int id) {
        LOGGER.info("The service deletes an answer");
        answerDao.delete(id);
    }
}
