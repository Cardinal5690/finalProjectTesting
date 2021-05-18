package com.testing.dao;

import com.testing.model.entity.Answer;

import java.util.List;

public interface AnswerDao extends GenericDao<Answer> {
    List<Answer> findAllByQuestionId(int questionId);
}
