package com.testing.dao;

import com.testing.model.entity.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Question> {
    List<Question> getAllQuestionsByTestName (String testName);
}
